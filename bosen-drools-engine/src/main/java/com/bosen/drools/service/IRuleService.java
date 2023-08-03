package com.bosen.drools.service;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/8/3
 */
public interface IRuleService {
    /**
     * 获取订单可使用的优惠券
     */
    void checkOrderAndAssignmentCoupon();

    /**
     * 校验并计算订单优惠价格
     */
    void checkOrderPromotion();

    /**
     * 生成rule字符串
     */
    void genRuleScript();


}
