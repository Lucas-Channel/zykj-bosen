package com.bosen.drools.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 规则列表明细实体类
 *
 * @author Lucas
 * @since 2023-05-15 14:10:48
 */
@Data
@TableName("bs_drools_condition_col_item")
@EqualsAndHashCode(callSuper = true)
public class DroolsConditionColItemDO extends BaseEntityData implements Serializable {
    private static final long serialVersionUID = -6630945425045911111L;

    /**
     * 规则条件列id
     */
    private String droolsConditionColId;

    /**
     * 规则列明细编码
     */
    private String droolsConditionColItemCode;

    /**
     * 规则列明细名称
     */
    private String droolsConditionColItemName;

    /**
     * 描述
     */
    private String droolsConditionColItemDesc;

    /**
     * 条件具体字段，比如SalesOrder中的orderAmt
     */
    private String droolsConditionColItemColName;

    /**
     * 具体条件字段key
     */
    private String droolsConditionColItemColNameKey;

    /**
     * 条件操作符，比如 >=, memberOf等等
     */
    private String droolsConditionColItemOperator;

    /**
     * 条件操作符key
     */
    private String droolsConditionColItemOperatorKey;

    /**
     * 条件明细列类型：input select date等等
     */
    private String fieldType;

    /**
     * 接口地址
     */
    private String fieldSelectUrl;

    /**
     * 传递的值的key
     */
    private String fieldSelectVf;

    /**
     * 显示值的key
     */
    private String fieldSelectTf;

    /**
     * 是否多选
     */
    private Boolean multiFlag = false;

    /**
     * 拼接符号
     */
    private String spliceSymbol;

    /**
     * 默认值
     */
    private String defaultVal;

    /**
     * 启用状态：1，启用，0禁用
     */
    private Integer status;
}

