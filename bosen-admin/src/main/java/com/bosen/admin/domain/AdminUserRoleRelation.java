package com.bosen.admin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 平台后台用户-角色信息表
 */
@Data
@TableName("bs_user_role_relation")
public class AdminUserRoleRelation implements Serializable {
    private static final long serialVersionUID = -5251127089052356920L;
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    private String adminUserId;

    private String roleId;

    /**
     * 是否是默认角色
     */
    private Integer defaultRole;

}