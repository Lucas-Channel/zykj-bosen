package com.bosen.admin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("bs_role")
public class BsRole implements Serializable {

    private static final long serialVersionUID = 4885030293462585135L;

    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 启用状态：0->禁用；1->启用
     */
    private Integer status;

    /**
     * 登录默认选中角色id
     */
    @TableField(exist = false)
    private Long defaultRole;
}