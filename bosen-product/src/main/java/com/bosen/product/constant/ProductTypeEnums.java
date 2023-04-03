package com.bosen.product.constant;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/3
 */
public enum ProductTypeEnums {

    GIFT(1, "赠品"),
    PRODUCT_PACKAGE(2, "商品包"),
    PRODUCT(3, "普通商品"),
    SERVICE_PRODUCT(4, "服务商品"),
    ;

    ProductTypeEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;

    private static Map<Integer,String> messageMap = Arrays.stream(ProductTypeEnums.values()).collect(Collectors.toMap(ProductTypeEnums::getCode,ProductTypeEnums::getMessage));

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
