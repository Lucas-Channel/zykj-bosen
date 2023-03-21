package com.bosen.admin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色-权限
 */
@Data
@TableName("bs_role_permission_relation")
public class SystemRolePermissionRelation implements Serializable {
    private static final long serialVersionUID = -3527875419934051243L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long roleId;

    private Long permissionId;

}