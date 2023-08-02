package com.bosen.drools.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 规则结果列表(BsDroolsResultCol)表实体类
 *
 * @author Lucas
 * @since 2023-05-15 14:10:48
 */
@Data
@TableName("bs_drools_action_col")
@EqualsAndHashCode(callSuper = true)
public class DroolsActionColDO extends BaseEntityData implements Serializable {
    private static final long serialVersionUID = 2130171630226580676L;
    /**
     * 规则结果列编码
     */
    private String droolsActionColCode;

    /**
     * 规则结果列名称
     */
    private String droolsActionColName;

    /**
     * 描述
     */
    private String droolsActionColDesc;

    /**
     * 应用层面类型
     */
    private Integer type;

    /**
     * 启用状态：1，启用，0禁用
     */
    private Integer status;
}

