package com.bosen.message.constant;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 消息群体类型 枚举值
 * @author Lucas
 * @version 2.0.0
 * @date 2023/8/14
 */
public enum MessageTargetGroupTypeConstant {

    /**
     * 所有用户
     */
    ALL(1, "所有"),

    /**
     * 指定用户
     */
    DIRECTED_USER(2, "指定用户"),

    /**
     * 指定角色
     */
    DIRECTED_ROLE(3, "指定角色"),
    ;

    private final Integer code;
    private final String message;

    private static final Map<Integer,String> messageMap = Arrays.stream(MessageTargetGroupTypeConstant.values()).collect(Collectors.toMap(MessageTargetGroupTypeConstant::getCode,MessageTargetGroupTypeConstant::getMessage));

    public static String getMessageByCode(Integer code) {
        return messageMap.get(code);
    }

    MessageTargetGroupTypeConstant(int code, String message) {
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
