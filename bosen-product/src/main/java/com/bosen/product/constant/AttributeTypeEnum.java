package com.bosen.product.constant;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 商品属性类型枚举
 */
public enum AttributeTypeEnum {

    SPEC(1, "规格"),
    ATTR(2, "属性");

    AttributeTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private final Integer code;
    private final String message;

    private static final Map<Integer,String> messageMap = Arrays.stream(AttributeTypeEnum.values()).collect(Collectors.toMap(AttributeTypeEnum::getCode,AttributeTypeEnum::getMessage));

    public static String getMessageByCode(Integer code) {
        return messageMap.get(code);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
