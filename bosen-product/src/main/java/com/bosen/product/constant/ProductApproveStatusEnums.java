package com.bosen.product.constant;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/3
 */
public enum ProductApproveStatusEnums {

    WAIT_SUBMIT_APPROVE(0, "待提交审核"),
    WAIT_APPROVE(1, "待审核"),
    DISAGREE(2, "审核不通过"),
    AGREE(3, "审核通过"),
    DOWN(4, "已下架"),
    UP(4, "已上架"),
    ;

    ProductApproveStatusEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;

    private static Map<Integer,String> messageMap = Arrays.stream(ProductApproveStatusEnums.values()).collect(Collectors.toMap(ProductApproveStatusEnums::getCode, ProductApproveStatusEnums::getMessage));

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
