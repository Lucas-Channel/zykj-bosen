package com.bosen.drools.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 规则结果列表明细实体类
 *
 * @author Lucas
 * @since 2023-05-15 14:10:48
 */
@Data
@TableName("bs_drools_action_col_item")
@EqualsAndHashCode(callSuper = true)
public class DroolsActionColItemDO extends BaseEntityData implements Serializable {
    private static final long serialVersionUID = 6931518787186483281L;

    /**
     * 规则结果列id
     */
    private String droolsActionColId;

    /**
     * 规则结果列编码
     */
    private String droolsResultColCode;

    /**
     * 规则结果列名称
     */
    private String droolsResultColName;

    /**
     * 规则结果列代码
     */
    private String droolsResultColScript;

    /**
     * 描述
     */
    private String droolsResultColDesc;

    /**
     * 启用状态：1，启用，0禁用
     */
    private Integer status;
}
