package com.bosen.admin.constant;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 店铺状态
 */
public enum StoreOpenCloseStatusEnum {

    WAIT_APPROVE_OPEN_STORE_APPLY(1, "待审核开店申请"),
    OPEN_STORE_APPLY_APPROVE_PASS(2, "开店申请审核通过"),
    OPEN_STORE_APPLY_APPROVE_REFUSE(3, "开店申请审核不通过"),
    WAIT_APPROVE_CLOSE_STORE_APPLY(4, "待审核关店申请"),
    CLOSE_STORE_APPLY_APPROVE_PASS(5, "关店申请审核通过"),
    CLOSE_STORE_APPLY_APPROVE_REFUSE(6, "关店申请审核不通过"),
    FROZEN(7, "冻结"),
    ;

    StoreOpenCloseStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;

    private static Map<Integer,String> messageMap = Arrays.stream(StoreOpenCloseStatusEnum.values()).collect(Collectors.toMap(StoreOpenCloseStatusEnum::getCode, StoreOpenCloseStatusEnum::getMessage));

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
