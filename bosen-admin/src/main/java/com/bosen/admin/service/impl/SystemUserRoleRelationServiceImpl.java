package com.bosen.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.admin.domain.SystemRole;
import com.bosen.admin.domain.SystemUserRoleRelation;
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
public class SystemUserRoleRelationServiceImpl extends ServiceImpl<SystemUserRoleRelationMapper, SystemUserRoleRelation> implements ISystemUserRoleRelationService {
    @Override
    public List<SystemRole> getUserRoleList(Long adminUserId) {
        return this.baseMapper.getUserRoleList(adminUserId);
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public ResponseData<Void> updateUserRole(Long adminId, List<Long> roleIds) {
        // 删除历史角色
        boolean remove = this.lambdaUpdate().eq(SystemUserRoleRelation::getAdminUserId, adminId).remove();
        if (!remove) {
            throw new BusinessException(ResponseCode.DELETE_OLD_ROLES_ERROR);
        }
        List<SystemUserRoleRelation> userRoleRelations = roleIds.stream().map(i -> {
            SystemUserRoleRelation userRoleRelation = new SystemUserRoleRelation();
            userRoleRelation.setRoleId(i);
            userRoleRelation.setAdminUserId(adminId);
            return userRoleRelation;
        }).collect(Collectors.toList());
        return ResponseData.judge(this.saveOrUpdateBatch(userRoleRelations));
    }
}
