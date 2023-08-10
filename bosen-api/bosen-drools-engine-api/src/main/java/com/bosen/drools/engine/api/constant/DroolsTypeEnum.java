package com.bosen.drools.engine.api.constant;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/5/15
 */
public enum DroolsTypeEnum {
    /**
     * 优惠券
     */
    COUPON(1, "优惠券"),

    /**
     * 营销活动
     */
    MARKETING(2, "营销活动"),
    ;

    private final Integer code;
    private final String message;

    private static final Map<Integer,String> messageMap = Arrays.stream(DroolsTypeEnum.values()).collect(Collectors.toMap(DroolsTypeEnum::getCode,DroolsTypeEnum::getMessage));

    public static String getMessageByCode(Integer code) {
        return messageMap.get(code);
    }

    DroolsTypeEnum(int code, String message) {
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
