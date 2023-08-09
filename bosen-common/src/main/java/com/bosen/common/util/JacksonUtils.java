package com.bosen.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 序列化工具类
 * @author Lucas
 * @version 2.0.0
 * @date 2023/8/9
 */
public class JacksonUtils {

    // 私有构造函数，防止实例化
    private JacksonUtils() {
    }

    // 对象转 JSON 字符串
    public static String toJson(Object obj, ObjectMapper objectMapper) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    // JSON 字符串转对象
    public static <T> T fromJson(String json, Class<T> clazz, ObjectMapper objectMapper) throws JsonProcessingException {
        return objectMapper.readValue(json, clazz);
    }
}
