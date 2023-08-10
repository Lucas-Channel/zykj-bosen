package com.bosen.drools.engine.api.vo.request;

import com.bosen.drools.engine.api.constant.DroolsTypeEnum;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * 生成脚本-对象传输
 * @author Lucas
 * @version 2.0.0
 * @date 2023/8/8
 */
@Data
public class GenRuleScriptReqVO implements Serializable {

    private static final long serialVersionUID = 429430573861557659L;

    /**
     * 规则有效期起，当使用日期时，需要设置system.setProperty("drools.dateformate","格式")
     */
    private String effectiveStartDate;

    /**
     * 规则有效期止，当使用日期时，需要设置system.setProperty("drools.dateformate","格式")
     */
    private String effectiveEndDate;

    /**
     * 优惠券编码
     */
    private String couponCode;

    @Valid
    @NotEmpty(message = "条件不能为空")
    private List<GenConditionParamVO> conditionParams;

    @Valid
    @NotEmpty(message = "结果不能为空")
    private List<GenActionParamVO> actionParams;

    /**
     * 规则类型：1、优惠券规则，2、营销活动规则
     * @see DroolsTypeEnum
     */
    private Integer droolsType;

    /**
     * 数据源id
     */
    private String dataSourceId;
}
