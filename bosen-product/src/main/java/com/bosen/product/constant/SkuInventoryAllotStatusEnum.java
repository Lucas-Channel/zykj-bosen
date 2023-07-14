package com.bosen.product.constant;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * sku库存调拨状态枚举值
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/3
 */
public enum SkuInventoryAllotStatusEnum {

    WAIT_CONFIRM(0, "待确认"),
    WAIT_APPROVE(1, "待审核"),
    WAIT_OUT(2, "待出库"),
    WAIT_RECEIVE(3, "待收货"),
    HAS_RECEIVE(4, "已收货"),
    CANCEL(5, "已取消"),
    APPROVE_REJECT(6, "审核不通过"),
    ;

    SkuInventoryAllotStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private final Integer code;
    private final String message;

    private static final Map<Integer,String> messageMap = Arrays.stream(SkuInventoryAllotStatusEnum.values()).collect(Collectors.toMap(SkuInventoryAllotStatusEnum::getCode, SkuInventoryAllotStatusEnum::getMessage));

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
