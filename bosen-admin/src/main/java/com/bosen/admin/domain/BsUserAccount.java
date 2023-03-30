package com.bosen.admin.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户账户表(BsUserAccount)实体类
 *
 * @author gaofeicm
 * @since 2023-03-30 22:27:07
 */
@Data
@Accessors(chain = true)
@TableName("bs_user_account")
public class BsUserAccount extends BaseEntityData implements Serializable {

    private static final long serialVersionUID = -91825670664020709L;
    
    /**
    * 用户名
    */    
    @TableField("username")
    private String username;
        
    /**
    * 密码
    */    
    @TableField("password")
    private String password;
        
    /**
    * 手机号
    */    
    @TableField("mobile")
    private String mobile;
        
    /**
    * 昵称
    */    
    @TableField("nick_name")
    private String nickName;
        
    /**
    * 状态
    */    
    @TableField("status")
    private Integer status;
        
    /**
    * 最后登录时间
    */    
    @TableField("login_time")
    private Date loginTime;
        
    /**
    * 最后登出时间
    */    
    @TableField("logout_time")
    private Date logoutTime;
    
}
