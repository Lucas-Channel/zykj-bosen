package com.bosen.admin.controller;

import com.bosen.admin.domain.SystemMenuPermissionRelation;
import com.bosen.admin.service.ISystemMenuPermissionRelationService;
import com.bosen.admin.vo.response.SystemMenuPermissionDetail;
import com.bosen.admin.vo.resquest.MenuPermissionQueryVO;
import com.bosen.admin.vo.resquest.MenuPermissionUpsertVO;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.util.Asserts;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Collections;

/**
 * 菜单权限管理
 * @author Lucas
 */
@RestController
@Slf4j
@RequestMapping("/menu/permission")
public class SystemMenuPermissionController {

    @Resource
    private ISystemMenuPermissionRelationService menuPermissionRelationService;

    /**
     * 分页
     * @param query 参数
     * @return 结果
     */
    @GetMapping("/listPage")
    public ResponseData<PageData<SystemMenuPermissionDetail>> listPermissionsWithPage(@Valid MenuPermissionQueryVO query) {
        return menuPermissionRelationService.listPermissionsWithPage(query);
    }

    /**
     * 权限详情
     * @param permissionId 结果
     * @return 结果
     */
    @GetMapping("/detail/{permissionId}")
    public ResponseData<SystemMenuPermissionDetail> getPermissionDetail(@PathVariable Long permissionId) {
        Asserts.notNull(permissionId, "权限id不能为空");
        SystemMenuPermissionRelation permission = this.menuPermissionRelationService.lambdaQuery().eq(SystemMenuPermissionRelation::getId, permissionId).one();
        SystemMenuPermissionDetail detail = new SystemMenuPermissionDetail();
        BeanUtils.copyProperties(permission, detail);
        return ResponseData.success(detail);
    }

    /**
     * 新增/修改权限
     * @param permission 参数
     * @return 结果
     */
    @PostMapping("/upsertPermission")
    public ResponseData<Void> upsertPermission(@RequestBody @Valid MenuPermissionUpsertVO permission) {
        return menuPermissionRelationService.upsertPermission(permission);
    }


    /**
     * 删除权限
     * @param ids 权限ID，多个以英文逗号(,)分割
     * @return 结果
     */
    @DeleteMapping("/{ids}")
    public ResponseData<Void> deletePermissions(@PathVariable String ids) {
        return ResponseData.judge(menuPermissionRelationService.removeByIds(Collections.singletonList(ids.split(","))));
    }
}
