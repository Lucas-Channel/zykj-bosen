package com.bosen.drools.service;

import com.bosen.common.constant.response.ResponseData;
import com.bosen.drools.engine.api.vo.request.GenRuleScriptReqVO;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/8/3
 */
public interface IRuleService {
    /**
     * 获取订单可使用的优惠券
     */
    ResponseData<Void> checkOrderAndAssignmentCoupon();

    /**
     * 校验并计算订单优惠价格
     */
    ResponseData<Void> checkOrderPromotion();

    /**
     * 生成rule字符串
     */
    ResponseData<Void> genRuleScript(GenRuleScriptReqVO genRuleScriptReqVO);


}
