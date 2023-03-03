package com.bosen.common.constant.pay;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 交易类型
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/1
 */
public enum PayDealType {
    /**
     * 账户充值
     */
    Pay_Recharge(1, "账户充值"),

    /**
     * 账户提现
     */
    Pay_Cash_Out(2, "账户提现"),

    /**
     * 订单支付
     */
    Pay_Order(3, "订单支付"),

    /**
     * 订单退款
     */
    Order_Refund(4, "订单退款"),

    ;

    private final Integer code;
    private final String message;

    private static Map<Integer,String> messageMap = Arrays.stream(PayDealType.values()).collect(Collectors.toMap(PayDealType::getCode,PayDealType::getMessage));

    public static String getMessageByCode(Integer code) {
        return messageMap.get(code);
    }

    PayDealType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
