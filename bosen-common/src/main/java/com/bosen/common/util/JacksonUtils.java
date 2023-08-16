package com.bosen.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 序列化工具类
 * @author Lucas
 * @version 2.0.0
 * @date 2023/8/9
 */
@Component
public class JacksonUtils {

    private static ObjectMapper objectMapper;

    @Resource
    private ObjectMapper initObjectMapper;

    @PostConstruct
    public void init() {
        objectMapper = initObjectMapper;
    }

    // 私有构造函数，防止实例化
    private JacksonUtils() {
    }

    // 对象转 JSON 字符串
    @SneakyThrows
    public static String toJson(Object obj) {
        return objectMapper.writeValueAsString(obj);
    }

    // JSON 字符串转对象
    @SneakyThrows
    public static <T> T fromJson(String json, Class<T> clazz) {
        return objectMapper.readValue(json, clazz);
    }
}
