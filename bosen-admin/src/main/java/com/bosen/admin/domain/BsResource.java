package com.bosen.admin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("bs_resource")
public class BsResource implements Serializable {

    private static final long serialVersionUID = 4885030293462585135L;

    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 父主键
     */
    private String parentId;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 配置
     */
    private String option;

    /**
     * 描述
     */
    private String description;

    /**
     * 启用状态：0->禁用；1->启用
     */
    private Integer status;
}