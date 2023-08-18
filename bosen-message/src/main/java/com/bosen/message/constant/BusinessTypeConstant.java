package com.bosen.message.constant;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/8/14
 */
public enum BusinessTypeConstant {

    /**
     * 交易
     */
    TRADE(1, "交易"),

    /**
     * 订单
     */
    ORDER(2, "订单"),

    /**
     * 售后
     */
    AFTER_SALE(3, "售后"),

    /**
     * 物流
     */
    LOGISTICS(4, "物流"),

    STOCK(5, "库存"),

    MEMBER(6, "会员"),

    OTHER(7, "其他"),

    SYSTEM(8, "系统"),
    ;

    private final Integer code;
    private final String message;

    private static final Map<Integer,String> messageMap = Arrays.stream(BusinessTypeConstant.values()).collect(Collectors.toMap(BusinessTypeConstant::getCode, BusinessTypeConstant::getMessage));

    public static String getMessageByCode(Integer code) {
        return messageMap.get(code);
    }

    BusinessTypeConstant(int code, String message) {
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
