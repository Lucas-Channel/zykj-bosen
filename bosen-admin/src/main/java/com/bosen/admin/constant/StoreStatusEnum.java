package com.bosen.admin.constant;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 店铺状态
 */
public enum StoreStatusEnum {

    NORMAL(1, "正常"),
    FROZEN(2, "冻结"),
    ;

    StoreStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;

    private static Map<Integer,String> messageMap = Arrays.stream(StoreStatusEnum.values()).collect(Collectors.toMap(StoreStatusEnum::getCode, StoreStatusEnum::getMessage));

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
