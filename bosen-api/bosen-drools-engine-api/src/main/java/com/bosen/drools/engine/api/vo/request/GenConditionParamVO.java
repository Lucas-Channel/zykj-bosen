package com.bosen.drools.engine.api.vo.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
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
     * 条件列模板
     */
    @NotEmpty
    private String droolsConditionTemplate;

    /**
     * 条件列id
     */
    @NotEmpty
    private String droolsConditionColId;


    /**
     * 条件具体字段，比如SalesOrder中的orderAmt
     */
    @NotEmpty
    private String droolsConditionColItemColName;

    /**
     * 具体条件字段key
     */
    @NotEmpty
    private String droolsConditionColItemColNameKey;

    /**
     * 条件操作符，比如 >=, memberOf等等
     */
    @NotEmpty
    private String droolsConditionColItemOperator;

    /**
     * 条件操作符key
     */
    @NotEmpty
    private String droolsConditionColItemOperatorKey;

    /**
     * 规则组
     */
    @NotEmpty
    private String groupName;

    /**
     * 规则限制值
     */
    @NotEmpty
    private String conditionVal;

    private String conditionValKey;

    /**
     * 优先级
     */
    private Integer priority = 0;


    /**
     * 条件对象名称，例如$SalesOrder,SalesOrder为点单entity
     */
    @NotEmpty
    private String droolsConditionColObjectName;

    /**
     * 条件对象路径
     */
    @NotEmpty
    private String droolsConditionColObjectNamePath;

}
