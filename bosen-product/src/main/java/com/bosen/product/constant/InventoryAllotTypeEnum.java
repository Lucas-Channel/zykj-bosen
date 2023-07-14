package com.bosen.product.constant;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 调拨类型，内调审核通过后收货即可，外调，需要生成物流单，进行发货
 * @author Lucas
 * @version 2.0.0
 * @date 2023/7/14
 */
public enum InventoryAllotTypeEnum {

    WAIT_SUBMIT_APPROVE_APPLY(1, "内调"),
    WAIT_APPROVE(2, "外调"),
    ;

    InventoryAllotTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private final Integer code;
    private final String message;

    private static final Map<Integer,String> messageMap = Arrays.stream(InventoryAllotTypeEnum.values()).collect(Collectors.toMap(InventoryAllotTypeEnum::getCode, InventoryAllotTypeEnum::getMessage));

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
