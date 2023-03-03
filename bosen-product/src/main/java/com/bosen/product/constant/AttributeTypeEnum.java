package com.bosen.product.constant;

import com.bosen.common.constant.common.SexConstant;
import lombok.Getter;

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

    private Integer code;
    private String message;

    private static Map<Integer,String> messageMap = Arrays.stream(AttributeTypeEnum.values()).collect(Collectors.toMap(AttributeTypeEnum::getCode,AttributeTypeEnum::getMessage));

    public static String getMessageByCode(Integer code) {
        return messageMap.get(code);
    }

    AttributeTypeEnum(int code, String message) {
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
