package com.bosen.product.constant;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 配送方式
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/7
 */
public enum DeliveryTypeEnum {
    DELIVERY(1, "物流"),
    PICK_UP(2, "自提"),
    NOT(3, "无需配送");

    DeliveryTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private final Integer code;
    private final String message;

    private static final Map<Integer,String> messageMap = Arrays.stream(DeliveryTypeEnum.values()).collect(Collectors.toMap(DeliveryTypeEnum::getCode, DeliveryTypeEnum::getMessage));

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
