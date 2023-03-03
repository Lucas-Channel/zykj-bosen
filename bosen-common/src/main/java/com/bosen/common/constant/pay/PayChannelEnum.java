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
public enum PayChannelEnum {
    /**
     * 微信
     */
    WeChat(1, "微信"),

    /**
     * 支付宝
     */
    AliPay(2, "支付宝"),

    ;

    private final Integer code;
    private final String message;

    private static Map<Integer,String> messageMap = Arrays.stream(PayChannelEnum.values()).collect(Collectors.toMap(PayChannelEnum::getCode,PayChannelEnum::getMessage));

    public static String getMessageByCode(Integer code) {
        return messageMap.get(code);
    }

    PayChannelEnum(int code, String message) {
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
