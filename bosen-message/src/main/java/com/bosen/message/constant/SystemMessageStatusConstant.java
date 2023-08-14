package com.bosen.message.constant;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 系统消息状态
 * @author Lucas
 * @version 2.0.0
 * @date 2023/8/14
 */
public enum SystemMessageStatusConstant {

    /**
     * 草稿
     */
    SAVE(1, "草稿"),

    /**
     * 已发布
     */
    PUBLISHED(2, "已发布"),

    /**
     * 作废
     */
    CANCEL(3, "作废"),

    /**
     * 已过期
     */
    EXPIRED(4, "已过期"),
    ;

    private final Integer code;
    private final String message;

    private static final Map<Integer,String> messageMap = Arrays.stream(SystemMessageStatusConstant.values()).collect(Collectors.toMap(SystemMessageStatusConstant::getCode, SystemMessageStatusConstant::getMessage));

    public static String getMessageByCode(Integer code) {
        return messageMap.get(code);
    }

    SystemMessageStatusConstant(int code, String message) {
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
