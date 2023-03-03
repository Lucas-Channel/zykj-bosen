package com.bosen.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.admin.domain.SystemRolePermissionRelation;
import com.bosen.admin.vo.response.RolePermissionDetailVO;
import com.bosen.admin.vo.resquest.RolePermissionUpsertVO;
import com.bosen.common.constant.response.ResponseData;

import java.util.List;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/25
 */
public interface ISystemRolePermissionRelationService extends IService<SystemRolePermissionRelation> {

    void initRolesInterfaceUrlMap();

    ResponseData<List<RolePermissionDetailVO>> getRolePermissionList(Long roleId, Long menuId);

    ResponseData<Void> updateRolePermission(RolePermissionUpsertVO rolePermissionUpsertVO);

}
