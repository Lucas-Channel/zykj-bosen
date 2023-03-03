package com.bosen.common.constant.common;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 性别枚举值
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/23
 */
public enum SexConstant {
    /**
     * 男人
     */
    PAYMENT(1, "男人"),

    /**
     * 女人
     */
    LEGAL_REP_SIGN(2, "女人"),

    /**
     * 未知
     */
    MOBILE(0, "未知"),
    ;

    private final Integer code;
    private final String message;

    private static Map<Integer,String> messageMap = Arrays.stream(SexConstant.values()).collect(Collectors.toMap(SexConstant::getCode,SexConstant::getMessage));

    public static String getMessageByCode(Integer code) {
        return messageMap.get(code);
    }

    SexConstant(int code, String message) {
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
