package com.bosen.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.admin.domain.BsRole;
import com.bosen.admin.domain.BsUserAccount;
import com.bosen.admin.mapper.BsUserAccountMapper;
import com.bosen.admin.service.IBsUserAccountService;
import com.bosen.admin.service.ISystemUserRoleRelationService;
import com.bosen.admin.vo.resquest.RegisterAdminUserVO;
import com.bosen.admin.vo.resquest.UpdateAdminPasswordParamVO;
import com.bosen.common.constant.common.UserStatusConstant;
import com.bosen.common.constant.common.YesOrNoConstant;
import com.bosen.common.constant.response.ResponseCode;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.domain.AssignRoleVO;
import com.bosen.common.domain.BsUserAccountCacheVO;
import com.bosen.common.domain.UserDto;
import com.bosen.common.exception.BusinessException;
import com.bosen.common.service.IBsUserAccountCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 用户账户表(BsUserAccount)实现类
 *
 * @author gaofeicm
 * @since 2023-03-30 22:27:07
 */
@Slf4j
@Service
public class BsUserAccountServiceImpl extends ServiceImpl<BsUserAccountMapper, BsUserAccount> implements IBsUserAccountService {

    @Resource
    private ISystemUserRoleRelationService userRoleRelationService;

    @Resource
    private IBsUserAccountCacheService cacheService;

    @Override
    public UserDto loadUserByUsername(String username) {
        BsUserAccount adminUserDO = this.lambdaQuery().eq(BsUserAccount::getUsername, username).or().eq(BsUserAccount::getMobile, username).one();
        if (Objects.nonNull(adminUserDO)) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(adminUserDO, userDto);
            List<BsRole> userRoleList = userRoleRelationService.getUserRoleList(userDto.getId());
            if (!CollectionUtils.isEmpty(userRoleList)) {
                if (Objects.equals(userRoleList.size(), 1)) {
                    userDto.setDefaultRoleId(userRoleList.get(0).getId());
                } else {
                    List<BsRole> collect = userRoleList.stream().filter(i -> Objects.equals(YesOrNoConstant.YES.longValue(), i.getDefaultRole())).collect(Collectors.toList());
                    if (CollUtil.isNotEmpty(collect)) {
                        userDto.setDefaultRoleId(collect.get(0).getId());
                    } else {
                        userDto.setDefaultRoleId(userRoleList.get(0).getId());
                    }
                }
                userDto.setRoles(userRoleList.stream().map(i -> {
                    AssignRoleVO as = new AssignRoleVO();
                    as.setRoleId(i.getId());
                    as.setRoleCode(i.getCode());
                    return as;
                }).collect(Collectors.toList()));
            }
            return userDto;
        }
        return null;
    }

    @Override
    public ResponseData<Void> register(RegisterAdminUserVO registerAdminUserVO) {
        // 查询是否已存在该用户
        BsUserAccount one = this.lambdaQuery().eq(BsUserAccount::getUsername, registerAdminUserVO.getUsername()).one();
        if (Objects.nonNull(one)) {
            throw new BusinessException(ResponseCode.USER_NAME_HAS_EXIT_ERROR);
        }
        BsUserAccount userDO = new BsUserAccount();
        BeanUtils.copyProperties(registerAdminUserVO, userDO);
        userDO.setCreateTime(LocalDateTime.now());
        userDO.setStatus(UserStatusConstant.ACTIVE_STATUS);
        userDO.setPassword(BCrypt.hashpw(registerAdminUserVO.getPassword()));
        return ResponseData.judge(this.save(userDO));
    }

    @Override
    public ResponseData<Void> updatePassword(UpdateAdminPasswordParamVO updatePasswordParam) {
        BsUserAccount userDO = this.baseMapper.selectById(updatePasswordParam.getUserId());
        if (Objects.isNull(userDO)) {
            throw new BusinessException(ResponseCode.USER_NAME_NOT_EXIT_ERROR);
        }
        if (!BCrypt.checkpw(updatePasswordParam.getOldPassword(), userDO.getPassword())) {
            throw new BusinessException(ResponseCode.OLD_PASSWORD_ERROR);
        }
        userDO.setPassword(BCrypt.hashpw(updatePasswordParam.getNewPassword()));
        cacheService.delAdmin(userDO.getId());
        return ResponseData.judge(this.updateById(userDO));
    }

    @Override
    public BsUserAccountCacheVO getCurrentAdminUser(String adminId) {
        BsUserAccountCacheVO admin = cacheService.getAdmin(adminId);
        if (Objects.nonNull(admin)) {
            return admin;
        }
        admin = new BsUserAccountCacheVO();
        BsUserAccount adminUserDO = this.getById(adminId);
        BeanUtils.copyProperties(adminUserDO,admin);
        cacheService.setAdmin(admin);
        return admin;
    }
}
