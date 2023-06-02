package com.bosen.common.constant.pay;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 支付方式
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/1
 */
public enum PayMethodEnum {
    /**
     * 微信
     */
    WeChat("WeChat", "微信支付"),

    /**
     * 支付宝
     */
    AliPay("AliPay", "支付宝支付"),

    /**
     * 线下
     */
    Offline("Offline", "线下支付线上确认"),

    /**
     * 无需支付
     */
    Free("Free", "无需支付"),

    ;

    private final String code;
    private final String message;

    private static final Map<String,String> messageMap = Arrays.stream(PayMethodEnum.values()).collect(Collectors.toMap(PayMethodEnum::getCode, PayMethodEnum::getMessage));

    public static String getMessageByCode(Integer code) {
        return messageMap.get(code);
    }

    PayMethodEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
