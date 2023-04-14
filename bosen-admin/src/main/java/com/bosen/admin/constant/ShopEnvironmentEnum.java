package com.bosen.admin.constant;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 商城环境:1.web 2.H5 3.小程序 4.APP
 */
public enum ShopEnvironmentEnum {

    WEB(1, "web"),
    H5(2, "H5"),
    MINI_PROGRAM(3, "小程序"),
    APP(4, "APP"),
    ;

    ShopEnvironmentEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;

    private static Map<Integer,String> messageMap = Arrays.stream(ShopEnvironmentEnum.values()).collect(Collectors.toMap(ShopEnvironmentEnum::getCode,ShopEnvironmentEnum::getMessage));

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
