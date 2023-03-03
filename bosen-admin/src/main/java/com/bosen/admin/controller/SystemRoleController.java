package com.bosen.admin.controller;

import com.bosen.admin.domain.SystemRole;
import com.bosen.admin.service.ISystemRoleService;
import com.bosen.admin.vo.response.RoleDetailVO;
import com.bosen.admin.vo.resquest.RoleQueryVO;
import com.bosen.admin.vo.resquest.RoleUpsertVO;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Collections;



/**
 * 角色管理
 */
@RestController
@RequestMapping("/role")
public class SystemRoleController {

    @Resource
    private ISystemRoleService roleService;

    /**
     * 分页
     * @param queryVO 查询参数
     * @return 结果
     */
    @GetMapping("/page")
    public ResponseData<PageData<RoleDetailVO>> getRolePageList(RoleQueryVO queryVO) {
        return roleService.getRolePageList(queryVO);
    }

    /**
     * 角色详情
     * @param roleId 角色id
     * @return 明细
     */
    @GetMapping("/detail/{roleId}")
    public ResponseData<RoleDetailVO> getRoleDetail(@PathVariable Long roleId) {
        SystemRole role = roleService.getById(roleId);
        RoleDetailVO detailVO = new RoleDetailVO();
        BeanUtils.copyProperties(role, detailVO);
        return ResponseData.success(detailVO);
    }

    /**
     * 新增/修改角色
     * @param role 参数
     * @return 结果
     */
    @PostMapping("/upsertRole")
    public ResponseData<Void> upsertRole(@RequestBody @Valid RoleUpsertVO role) {
        return roleService.upsertRole(role);
    }

    /**
     * 删除角色
     * @param ids 删除角色，多个以英文逗号(,)分割
     * @return 结果
     */
    @DeleteMapping("/delete/{ids}")
    public ResponseData<Void> deleteRoles(@PathVariable String ids) {
        return ResponseData.judge(roleService.removeBatchByIds(Collections.singletonList(ids.split(","))));
    }
}
