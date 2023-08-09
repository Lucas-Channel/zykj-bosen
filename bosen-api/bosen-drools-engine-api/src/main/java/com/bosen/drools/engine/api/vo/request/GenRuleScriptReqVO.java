package com.bosen.drools.engine.api.vo.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;
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
     * 规则有效期起
     */
    private LocalDateTime effectiveStartDate;

    /**
     * 规则有效期止
     */
    private LocalDateTime effectiveEndDate;

    /**
     * 优惠券编码
     */
    private String couponCode;

    @NotEmpty(message = "条件不能为空")
    private List<GenConditionParamVO> conditionParams;

    @NotEmpty(message = "结果不能为空")
    private List<GenActionParamVO> actionParams;
}
