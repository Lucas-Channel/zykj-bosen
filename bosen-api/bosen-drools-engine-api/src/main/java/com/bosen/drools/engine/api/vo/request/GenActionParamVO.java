package com.bosen.drools.engine.api.vo.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
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
     * 结果列模板
     */
    @NotEmpty
    private String droolsActionTemplate;

    /**
     * 结果列id
     */
    @NotEmpty
    private String droolsActionColId;

    /**
     * 结果具体字段，比如SalesOrder中的orderAmt
     */
    @NotEmpty
    private String droolsActionColItemColName;

    /**
     * 具体结果字段key
     */
    @NotEmpty
    private String droolsActionColItemColNameKey;

    /**
     * 规则组
     */
    @NotEmpty
    private String groupName;

    /**
     * 规则限制值
     */
    @NotEmpty
    private String actionVal;

    @NotEmpty
    private String actionValKey;

    /**
     * 优先级
     */
    private Integer priority = 0;


    /**
     * 结果对象名称，例如$SalesOrder,SalesOrder为点单entity
     */
    @NotEmpty
    private String droolsActionColObjectName;

}
