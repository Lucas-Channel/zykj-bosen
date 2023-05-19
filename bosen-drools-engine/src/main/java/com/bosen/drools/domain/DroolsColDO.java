package com.bosen.drools.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 规则列表(BsDroolsCol)表实体类
 *
 * @author Lucas
 * @since 2023-05-15 14:10:48
 */
@Data
@TableName("bs_drools_col")
@EqualsAndHashCode(callSuper = true)
public class DroolsColDO extends BaseEntityData implements Serializable {
    private static final long serialVersionUID = 22641156041657755L;
    /**
     * 规则列编码
     */
    private String droolsColCode;

    /**
     * 规则列名称
     */
    private String droolsColName;

    /**
     * 规则列代码
     */
    private String droolsColScript;

    /**
     * 描述
     */
    private String droolsColDesc;

    /**
     * 启用状态：1，启用，0禁用
     */
    private Integer status;
}

