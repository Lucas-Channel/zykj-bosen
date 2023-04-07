package com.bosen.product.constant;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 运费计算模式
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/7
 */
public enum FreightCalculateModelEnum {
    SALES(1, "卖家承担运费"),
    BUY(2, "买家承担运费");

    FreightCalculateModelEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;

    private static Map<Integer,String> messageMap = Arrays.stream(FreightCalculateModelEnum.values()).collect(Collectors.toMap(FreightCalculateModelEnum::getCode,FreightCalculateModelEnum::getMessage));

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
