package com.bosen.drools.engine.api.vo.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 规则结果
 * @author Lucas
 * @version 2.0.0
 * @date 2023/8/8
 */
@Data
public class GenActionParamVO implements Serializable {
    private static final long serialVersionUID = 1885391726568441546L;

    /**
     * 条件对象名称，例如$SalesOrder,SalesOrder为点单entity
     */
    private String droolsActionColItemObjectName;

    /**
     * 条件具体字段，比如SalesOrder中的orderAmt
     */
    private String droolsActionColItemKey;

    /**
     * 脚本
     */
    private String droolsActionColItemScript;

    /**
     * 条件操作符，比如 >=, memberOf等等
     */
    private String droolsActionColItemOperator;

    /**
     * 规则组
     */
    private String groupName;

    /**
     * 规则结果值
     */
    private String actionVal;

    /**
     * 优先级
     */
    private Integer priority = 0;
}
