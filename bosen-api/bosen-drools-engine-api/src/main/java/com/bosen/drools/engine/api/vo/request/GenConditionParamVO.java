package com.bosen.drools.engine.api.vo.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 规则条件
 * @author Lucas
 * @version 2.0.0
 * @date 2023/8/8
 */
@Data
public class GenConditionParamVO implements Serializable {
    private static final long serialVersionUID = 6277356876186664564L;


    /**
     * 条件对象名称，例如$SalesOrder,SalesOrder为点单entity
     */
    private String droolsConditionColItemObjectName;

    /**
     * 条件具体字段，比如SalesOrder中的orderAmt
     */
    private String droolsConditionColItemKey;

    /**
     * 脚本
     */
    private String droolsConditionColItemScript;

    /**
     * 条件操作符，比如 >=, memberOf等等
     */
    private String droolsConditionColItemOperator;

    /**
     * 规则组
     */
    private String groupName;

    /**
     * 规则限制值
     */
    private String conditionVal;

    /**
     * 优先级
     */
    private Integer priority = 0;

}
