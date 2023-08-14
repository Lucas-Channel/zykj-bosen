package com.bosen.message.constant;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户消息状态
 * @author Lucas
 * @version 2.0.0
 * @date 2023/8/14
 */
public enum UserMessageStatusConstant {

    /**
     * 未读
     */
    UNREAD(1, "未读"),

    /**
     * 已读
     */
    READ(2, "已读"),
    ;

    private final Integer code;
    private final String message;

    private static final Map<Integer,String> messageMap = Arrays.stream(UserMessageStatusConstant.values()).collect(Collectors.toMap(UserMessageStatusConstant::getCode, UserMessageStatusConstant::getMessage));

    public static String getMessageByCode(Integer code) {
        return messageMap.get(code);
    }

    UserMessageStatusConstant(int code, String message) {
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
