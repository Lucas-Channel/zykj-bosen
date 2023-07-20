package com.bosen.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.admin.domain.AdminUserDO;
import com.bosen.admin.domain.BsRole;
import com.bosen.admin.mapper.AdminUserMapper;
import com.bosen.admin.service.IAdminUserService;
import com.bosen.admin.service.ISystemUserRoleRelationService;
import com.bosen.admin.vo.resquest.RegisterAdminUserVO;
import com.bosen.admin.vo.resquest.UpdateAdminPasswordParamVO;
import com.bosen.common.constant.common.UserStatusConstant;
import com.bosen.common.constant.common.YesOrNoConstant;
import com.bosen.common.constant.response.ResponseCode;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.domain.AdminUserCacheVO;
import com.bosen.common.domain.AssignRoleVO;
import com.bosen.common.domain.UserDto;
import com.bosen.common.exception.BusinessException;
import com.bosen.common.service.IAdminUserCacheService;
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
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/23
 */
@Slf4j
@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUserDO> implements IAdminUserService {

    @Resource
    private ISystemUserRoleRelationService userRoleRelationService;

    @Resource
    private IAdminUserCacheService cacheService;

    @Override
    public UserDto loadUserByUsername(String username) {
        AdminUserDO adminUserDO = this.lambdaQuery().eq(AdminUserDO::getUsername, username).or().eq(AdminUserDO::getMobile, username).one();
        if (Objects.nonNull(adminUserDO)) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(adminUserDO, userDto);
            List<BsRole> userRoleList = userRoleRelationService.getUserRoleList(userDto.getId());
            if (!CollectionUtils.isEmpty(userRoleList)) {
                if (Objects.equals(userRoleList.size(), 1)) {
                    userDto.setDefaultRoleId(userRoleList.get(0).getId());
                } else {
                    List<BsRole> collect = userRoleList.stream().filter(i -> Objects.equals(YesOrNoConstant.YES, i.getDefaultRole())).collect(Collectors.toList());
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
        AdminUserDO one = this.lambdaQuery().eq(AdminUserDO::getUsername, registerAdminUserVO.getUsername()).one();
        if (Objects.nonNull(one)) {
            throw new BusinessException(ResponseCode.USER_NAME_HAS_EXIT_ERROR);
        }
        AdminUserDO userDO = new AdminUserDO();
        BeanUtils.copyProperties(registerAdminUserVO, userDO);
        userDO.setCreateTime(LocalDateTime.now());
        userDO.setStatus(UserStatusConstant.ACTIVE_STATUS);
        userDO.setPassword(BCrypt.hashpw(registerAdminUserVO.getPassword()));
        return ResponseData.judge(this.save(userDO));
    }

    @Override
    public ResponseData<Void> updatePassword(UpdateAdminPasswordParamVO updatePasswordParam) {
        AdminUserDO userDO = this.baseMapper.selectById(updatePasswordParam.getUserId());
        if (Objects.isNull(userDO)) {
            throw new BusinessException(ResponseCode.USER_NAME_NOT_EXIT_ERROR);
        }
        if (!BCrypt.checkpw(updatePasswordParam.getOldPassword(), userDO.getPassword())) {
            throw new BusinessException(ResponseCode.OLD_PASSWORD_ERROR);
        }
        userDO.setPassword(BCrypt.hashpw(updatePasswordParam.getNewPassword()));
        //cacheService.delAdmin(userDO.getId());
        return ResponseData.judge(this.updateById(userDO));
    }

    @Override
    public AdminUserCacheVO getCurrentAdminUser(Long adminId) {
        AdminUserCacheVO admin = cacheService.getAdmin(adminId);
        if (Objects.nonNull(admin)) {
            return admin;
        }
        admin = new AdminUserCacheVO();
        AdminUserDO adminUserDO = this.getById(adminId);
        BeanUtils.copyProperties(adminUserDO,admin);
        cacheService.setAdmin(admin);
        return admin;
    }
}
