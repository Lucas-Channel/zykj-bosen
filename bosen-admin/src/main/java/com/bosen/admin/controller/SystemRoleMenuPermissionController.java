package com.bosen.admin.controller;

import com.bosen.admin.service.ISystemRolePermissionRelationService;
import com.bosen.admin.vo.response.RolePermissionDetailVO;
import com.bosen.admin.vo.resquest.RolePermissionUpsertVO;
import com.bosen.common.constant.response.ResponseData;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;


/**
 * 角色菜单权限管理
 */
@RestController
@RequestMapping("/role/menu/permission")
public class SystemRoleMenuPermissionController {

    @Resource
    private ISystemRolePermissionRelationService rolePermissionRelationService;

    /**
     * 获取角色已分配-菜单权限-列表
     * @param roleId 角色id
     * @param menuId 菜单
     * @return 结果
     */
    @GetMapping("/getRolePermissionList")
    public ResponseData<List<RolePermissionDetailVO>> getRolePermissionList(Long roleId, Long menuId) {
        return rolePermissionRelationService.getRolePermissionList(roleId, menuId);
    }

    /**
     * 分配角色-菜单权限
     * @return 结果
     */
    @PostMapping("/updateRolePermission")
    public ResponseData<Void> updateRolePermission(@RequestBody @Valid RolePermissionUpsertVO rolePermissionUpsertVO) {
        return rolePermissionRelationService.updateRolePermission(rolePermissionUpsertVO);
    }

}
