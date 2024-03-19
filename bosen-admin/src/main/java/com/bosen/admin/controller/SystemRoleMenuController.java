package com.bosen.admin.controller;

import com.bosen.admin.service.ISystemRoleMenuRelationService;
import com.bosen.admin.vo.response.LoginMenuDetailVO;
import com.bosen.admin.vo.response.RoleMenuDetailVO;
import com.bosen.admin.vo.resquest.RoleMenuUpsertVO;
import com.bosen.common.constant.response.ResponseData;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * 角色菜单管理
 */
@RestController
@RequestMapping("/role/menu")
public class SystemRoleMenuController {

    @Resource
    private ISystemRoleMenuRelationService roleMenuRelationService;

    /**
     * 获取角色已分配-菜单-树形列表
     * @param roleId 角色id
     * @return 结果
     */
    @GetMapping("/getRoleMenuList")
    public ResponseData<List<RoleMenuDetailVO>> getRoleMenuList(String roleId) {
        return roleMenuRelationService.getRoleMenuList(roleId);
    }

    /**
     * 分配角色菜单权限
     * @return 结果
     */
    @PostMapping("/updateRoleMenu")
    public ResponseData<Void> updateRoleMenu(@RequestBody @Valid RoleMenuUpsertVO roleMenuUpsertVO) {
        return roleMenuRelationService.updateRoleMenu(roleMenuUpsertVO);
    }

    /**
     * 用户登录后获取菜单权限
     * @return 列表菜单权限
     */
    @GetMapping("/listMenuAfterLogin")
    public ResponseData<Map<String, Object>> listMenuAfterLogin(String roleId) {
        return roleMenuRelationService.listMenuAfterLogin(roleId);
    }
}
