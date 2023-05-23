package com.bosen.admin.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息表(BsUserInfo)实体类
 *
 * @author gaofeicm
 * @since 2023-03-30 22:32:04
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("bs_user_info")
public class BsUserInfo extends BaseEntityData implements Serializable {

    private static final long serialVersionUID = 931889159172259936L;
    
    /**
    * ID
    */    
    private String id;
    
    /**
    * 头像
    */    
    @TableField("icon")
    private String icon;
        
    /**
    * 邮箱
    */    
    @TableField("email")
    private String email;
        
    /**
    * 性别
    */    
    @TableField("sex")
    private Integer sex;
        
    /**
    * 生日
    */    
    @TableField("birth_date")
    private Date birthDate;
    
}
