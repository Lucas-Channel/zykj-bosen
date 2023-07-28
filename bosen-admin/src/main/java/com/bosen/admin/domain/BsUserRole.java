package com.bosen.admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户角色关联表(BsUserRole)实体类
 *
 * @author gaofeicm
 * @since 2023-04-10 22:41:00
 */
@Data
@TableName("bs_user_role")
public class BsUserRole implements Serializable {

    private static final long serialVersionUID = -22771009658708603L;
    
    /**
    * ID
    */    
    private String id;
    
    /**
    * 用户ID,关联bs_user_account.id
    */    
    @TableField("user_id")
    private String userId;
        
    /**
    * 角色ID,关联bs_role.id
    */    
    @TableField("role_id")
    private String roleId;

    /**
     * 客户端
     */
    @TableField("client")
    private String client;
        
    /**
    * 是否是默认角色，0-否，1-是
    */    
    @TableField("default_role")
    private Object defaultRole;
    
}
