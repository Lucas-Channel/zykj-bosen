package com.bosen.admin.vo.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色-菜单权限
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/26
 */
@Data
public class RolePermissionDetailVO implements Serializable {
    private static final long serialVersionUID = -2548314665581694237L;

    /**
     * 权限id
     */
    private Long permissionId;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 菜单排序
     */
    private Integer sort;

    /**
     * 图标
     */
    private String icon;

    /**
     * 是否分配当前角色
     */
    private Boolean hasMenuPower = false;
}
