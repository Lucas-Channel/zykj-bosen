package com.bosen.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.common.constant.response.ResponseCode;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.exception.BusinessException;
import com.bosen.common.util.AuthUser;
import com.bosen.member.domain.PortalMemberLevelDO;
import com.bosen.member.mapper.MemberLevelMapper;
import com.bosen.member.service.IMemberLevelService;
import com.bosen.member.vo.request.MemberLevelQueryVO;
import com.bosen.member.vo.request.MemberLevelUpsertVO;
import com.bosen.member.vo.response.MemberLevelDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 会员等级业务实现层
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/27
 */
@Slf4j
@Service
public class MemberLevelServiceImpl extends ServiceImpl<MemberLevelMapper, PortalMemberLevelDO> implements IMemberLevelService {

    @Override
    public ResponseData<PortalMemberLevelDO> getDefaultMemberLevel() {
        PortalMemberLevelDO memberLevelDO = this.lambdaQuery().eq(PortalMemberLevelDO::getDefaultStatus, 1).one();
        return ResponseData.success(memberLevelDO);
    }

    @Override
    public ResponseData<Boolean> checkExitDefaultMemberLevel() {
        return ResponseData.success(Objects.nonNull(this.lambdaQuery().eq(PortalMemberLevelDO::getDefaultStatus, 1).one()));
    }

    @Override
    public ResponseData<Void> upsertMemberLevel(MemberLevelUpsertVO memberLevelUpsertVO) {
        if (Objects.equals(memberLevelUpsertVO.getDefaultStatus(), 1) && checkExitDefaultMemberLevel().getData()) {
            throw new BusinessException(ResponseCode.HAS_EXIT_DEFAULT_MEMBER_LEVEL_ERROR);
        }
        PortalMemberLevelDO levelDO = new PortalMemberLevelDO();
        BeanUtils.copyProperties(memberLevelUpsertVO, levelDO);
        if (Objects.nonNull(memberLevelUpsertVO.getId())) {
            levelDO.setCreatorUser(AuthUser.getUsername());
            levelDO.setCreateTime(LocalDateTime.now());
        } else {
            levelDO.setUpdaterUser(AuthUser.getUsername());
            levelDO.setUpdateTime(LocalDateTime.now());
        }
        return ResponseData.judge(this.saveOrUpdate(levelDO));
    }

    @Override
    public ResponseData<List<MemberLevelDetailVO>> listMemberLevel(MemberLevelQueryVO queryVO) {
        List<PortalMemberLevelDO> list = this.lambdaQuery().like(StringUtils.hasLength(queryVO.getName()), PortalMemberLevelDO::getName, queryVO.getName())
                .eq(Objects.nonNull(queryVO.getDefaultStatus()), PortalMemberLevelDO::getDefaultStatus, queryVO.getDefaultStatus())
                .list();
        return ResponseData.success(list.stream().map( i -> {
            MemberLevelDetailVO detailVO = new MemberLevelDetailVO();
            BeanUtils.copyProperties(i, detailVO);
            return detailVO;
        }).collect(Collectors.toList()));
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public ResponseData<Void> deleteBatch(List<Long> ids) {
        return ResponseData.judge(this.removeBatchByIds(ids));
    }
}
