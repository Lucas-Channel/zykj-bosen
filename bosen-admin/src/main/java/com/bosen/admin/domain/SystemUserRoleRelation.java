package com.bosen.admin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("bs_user_role_relation")
public class SystemUserRoleRelation implements Serializable {
    private static final long serialVersionUID = -5251127089052356920L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long adminUserId;

    private Long roleId;

}