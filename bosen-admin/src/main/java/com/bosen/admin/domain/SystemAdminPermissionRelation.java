package com.bosen.admin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 后台用户和权限关系表(除角色中定义的权限以外的加减权限)
 */
@Data
@TableName("bs_user_permission_relation")
public class SystemAdminPermissionRelation implements Serializable {
    private static final long serialVersionUID = 534039626625806289L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long adminId;

    private Long permissionId;

}