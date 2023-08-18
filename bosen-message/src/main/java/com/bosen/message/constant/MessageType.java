package com.bosen.message.constant;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/8/18
 */
public enum MessageType {

    SYSTEM(1, "系统消息"),

    OTHER(2, "其他消息"),
            ;

    private final Integer code;
    private final String message;

    private static final Map<Integer,String> messageMap = Arrays.stream(MessageType.values()).collect(Collectors.toMap(MessageType::getCode, MessageType::getMessage));

    public static String getMessageByCode(Integer code) {
        return messageMap.get(code);
    }

    MessageType(int code, String message) {
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
