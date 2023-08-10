package com.bosen.drools.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 规则列条件表(BsDroolsCol)表实体类
 *
 * @author Lucas
 * @since 2023-05-15 14:10:48
 */
@Data
@TableName("bs_drools_condition_col")
@EqualsAndHashCode(callSuper = true)
public class DroolsConditionColDO extends BaseEntityData implements Serializable {
    private static final long serialVersionUID = 22641156041657755L;
    /**
     * 规则条件列编码
     */
    private String droolsConditionColCode;

    /**
     * 规则条件列名称
     */
    private String droolsConditionColName;

    /**
     * 描述
     */
    private String droolsConditionColDesc;

    /**
     * 脚本模板
     */
    private String droolsConditionTemplate;

    /**
     * 条件对象名称，例如$SalesOrder,SalesOrder为点单entity
     */
    private String droolsConditionColObjectName;

    /**
     * 条件对象路径
     */
    private String droolsConditionColObjectNamePath;

    /**
     * 应用层面类型
     */
    private Integer type;

    /**
     * 启用状态：1，启用，0禁用
     */
    private Integer status;
}

