package com.bosen.member.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.common.constant.common.DefaultPassword;
import com.bosen.common.constant.common.UserStatusConstant;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseCode;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.domain.AssignRoleVO;
import com.bosen.common.domain.MemberCacheVO;
import com.bosen.common.domain.UserDto;
import com.bosen.common.exception.BusinessException;
import com.bosen.common.service.IMemberCacheService;
import com.bosen.common.util.AuthUser;
import com.bosen.member.domain.PortalMemberDO;
import com.bosen.member.domain.PortalMemberLevelDO;
import com.bosen.member.mapper.PortalMemberMapper;
import com.bosen.member.service.IMemberLevelService;
import com.bosen.member.service.IPortalMemberService;
import com.bosen.member.vo.request.MemberQueryVO;
import com.bosen.member.vo.request.MemberRegisterVO;
import com.bosen.member.vo.request.MemberUpdateVO;
import com.bosen.member.vo.response.MemberDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/25
 */
@Slf4j
@Service
public class PortalMemberServiceImpl extends ServiceImpl<PortalMemberMapper, PortalMemberDO> implements IPortalMemberService {

    @Resource
    private IMemberLevelService memberLevelService;

    @Resource
    private IMemberCacheService memberCacheService;

    @Override
    public UserDto loadByUsername(String username) {
        PortalMemberDO memberDO = this.lambdaQuery().eq(PortalMemberDO::getUsername, username).or().eq(PortalMemberDO::getPhone, username).one();
        if (Objects.nonNull(memberDO)) {
            UserDto userDto = new UserDto();
            BeanUtil.copyProperties(memberDO,userDto);
            userDto.setRoles(CollUtil.toList(new AssignRoleVO(0L, "c端会员")));
            return userDto;
        }
        return null;
    }

    @Override
    public ResponseData<Void> register(MemberRegisterVO memberRegisterVO) {
        // 判断是否存在同名的用户
        PortalMemberDO one = this.lambdaQuery().eq(PortalMemberDO::getUsername, memberRegisterVO.getUsername()).or().eq(PortalMemberDO::getPhone, memberRegisterVO.getPhone()).one();
        if (Objects.nonNull(one)) {
            throw new BusinessException(ResponseCode.USER_NAME_OR_PHONE_HAS_EXIT_ERROR);
        }
        one = new PortalMemberDO();
        BeanUtils.copyProperties(memberRegisterVO, one);
        one.setPassword(BCrypt.hashpw(memberRegisterVO.getDefaultPassword() || !StringUtils.hasLength(memberRegisterVO.getPassword()) ? DefaultPassword.DEFAULT_PASSWORD : memberRegisterVO.getPassword()));
        one.setCreateTime(LocalDateTime.now());
        one.setStatus(UserStatusConstant.ACTIVE_STATUS);
        // 设置默认等级
        ResponseData<PortalMemberLevelDO> memberLevel = memberLevelService.getDefaultMemberLevel();
        if (Objects.nonNull(memberLevel.getData())) {
            //one.setMemberLevelId(memberLevel.getData().getId());
        }
        return ResponseData.judge(this.save(one));
    }

    @Override
    public ResponseData<PageData<MemberDetailVO>> pageMemberList(MemberQueryVO queryVO) {
        Page<PortalMemberDO> pageList = this.page(new Page<>(queryVO.getCurrent(), queryVO.getSize()), new LambdaQueryWrapper<PortalMemberDO>()
                .like(StringUtils.hasLength(queryVO.getPhone()), PortalMemberDO::getPhone, queryVO.getPhone())
                .like(StringUtils.hasLength(queryVO.getUsername()), PortalMemberDO::getUsername, queryVO.getUsername())
                .like(StringUtils.hasLength(queryVO.getNickName()), PortalMemberDO::getNickName, queryVO.getNickName())
                .eq(Objects.nonNull(queryVO.getMemberLevelId()), PortalMemberDO::getMemberLevelId, queryVO.getMemberLevelId())
                .eq(Objects.nonNull(queryVO.getStatus()), PortalMemberDO::getStatus, queryVO.getStatus())
                .eq(Objects.nonNull(queryVO.getGender()), PortalMemberDO::getGender, queryVO.getGender()));
        return ResponseData.success(new PageData<>(pageList.getTotal(), pageList.getRecords().stream().map(i -> {
            MemberDetailVO detailVO = new MemberDetailVO();
            BeanUtils.copyProperties(i, detailVO);
            return detailVO;
        }).collect(Collectors.toList())));
    }

    @Override
    public ResponseData<MemberDetailVO> getMemberInfo(Long id) {
        PortalMemberDO one = this.lambdaQuery().eq(PortalMemberDO::getId, id).one();
        if (Objects.nonNull(one)) {
            MemberDetailVO detailVO = new MemberDetailVO();
            BeanUtils.copyProperties(one, detailVO);
            return ResponseData.success(detailVO);
        }
        return ResponseData.success();
    }

    @Override
    public ResponseData<Void> updateMemberInfo(MemberUpdateVO updateVO) {
        // 会员只能更新自己的信息
        if (!Objects.equals(AuthUser.getUserId(), updateVO.getId())) {
            throw new BusinessException(ResponseCode.ONLY_UPDATE_MEMBER_SELF_INFO_ERROR);
        }
        PortalMemberDO memberDO = new PortalMemberDO();
        BeanUtils.copyProperties(updateVO, memberDO);
        return ResponseData.judge(this.updateById(memberDO));
    }

    @Override
    public ResponseData<Void> updateStatus(Long id, Integer status) {
        PortalMemberDO memberDO = new PortalMemberDO();
        //memberDO.setStatus(status).setId(id);
        return ResponseData.judge(this.updateById(memberDO));
    }

    @Override
    public MemberCacheVO getCurrentMember(Long memberId) {
        MemberCacheVO admin = memberCacheService.getMember(memberId);
        if (Objects.nonNull(admin)) {
            return admin;
        }
        admin = new MemberCacheVO();
        PortalMemberDO adminUserDO = this.getById(memberId);
        BeanUtils.copyProperties(adminUserDO,admin);
        memberCacheService.setMember(admin);
        return admin;
    }
}
