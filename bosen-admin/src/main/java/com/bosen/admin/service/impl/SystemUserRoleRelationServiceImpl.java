package com.bosen.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.admin.domain.AdminUserRoleRelation;
import com.bosen.admin.domain.BsRole;
import com.bosen.admin.mapper.SystemUserRoleRelationMapper;
import com.bosen.admin.service.ISystemUserRoleRelationService;
import com.bosen.common.constant.response.ResponseCode;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/25
 */
@Slf4j
@Service
public class SystemUserRoleRelationServiceImpl extends ServiceImpl<SystemUserRoleRelationMapper, AdminUserRoleRelation> implements ISystemUserRoleRelationService {
    @Override
    public List<BsRole> getUserRoleList(String adminUserId) {
        return this.baseMapper.getUserRoleList(adminUserId);
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public ResponseData<Void> updateUserRole(String adminId, List<String> roleIds) {
        // 删除历史角色
        boolean remove = this.lambdaUpdate().eq(AdminUserRoleRelation::getAdminUserId, adminId).remove();
        if (!remove) {
            throw new BusinessException(ResponseCode.DELETE_OLD_ROLES_ERROR);
        }
        List<AdminUserRoleRelation> userRoleRelations = roleIds.stream().map(i -> {
            AdminUserRoleRelation userRoleRelation = new AdminUserRoleRelation();
            userRoleRelation.setRoleId(i);
            userRoleRelation.setAdminUserId(adminId);
            return userRoleRelation;
        }).collect(Collectors.toList());
        return ResponseData.judge(this.saveOrUpdateBatch(userRoleRelations));
    }
}
