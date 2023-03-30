package com.bosen.admin.controller;

import com.bosen.admin.domain.SystemRole;
import com.bosen.admin.service.ISystemUserRoleRelationService;
import com.bosen.common.constant.response.ResponseData;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 后台用户角色管理
 */
@RestController
@RequestMapping("/admin/role")
public class UserRoleController {
    @Resource
    private ISystemUserRoleRelationService userRoleRelationService;

    /**
     * 给用户分配角色
     * @param adminId 用户id
     * @param roleIds 角色ids
     * @return
     */
    @PostMapping(value = "/allocation")
    public ResponseData<Void> updateRole(@RequestParam("adminId") String adminId, @RequestParam("roleIds") List<String> roleIds) {
        return userRoleRelationService.updateUserRole(adminId, roleIds);
    }

    /**
     * 获取指定用户的角色
     * @param adminId 用户id
     * @return 结果
     */
    @GetMapping(value = "/role/{adminId}")
    @ResponseBody
    public ResponseData<List<SystemRole>> getRoleList(@PathVariable String adminId) {
        List<SystemRole> roleList = userRoleRelationService.getUserRoleList(adminId);
        return ResponseData.success(roleList);
    }

}
