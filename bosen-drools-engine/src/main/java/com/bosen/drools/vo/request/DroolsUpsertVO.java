package com.bosen.drools.vo.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 新增修改规则
 * @author Lucas
 * @version 2.0.0
 * @date 2023/5/15
 */
@Data
@Accessors(chain = true)
public class DroolsUpsertVO implements Serializable {
    private static final long serialVersionUID = -4493811793246281460L;

    private String id;

    /**
     * 规则编码
     */
    private String droolsCode;

    /**
     * 规则名称
     */
    private String droolsName;

    /**
     * 规则代码
     */
    private String droolsScript;

    /**
     * 描述
     */
    private String droolsDesc;

    /**
     * 启用状态：1，启用，0禁用
     */
    private Integer status;

    /**
     * 规则类型：1、优惠券规则，2、营销活动规则
     */
    private Integer droolsType;
}
