package com.bosen.common.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 字符串单据好工具类
 * @author Lucas
 */
public class OrderNoStringUtil {

    private static final String key = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    /**
     * 生成单号
     * @param prefix 单号前缀
     * @return 字符串
     */
    public static String randomOrderNo(String prefix, Integer length) {
        return prefix.concat(RandomStringUtils.random(length, key));
    }

    /**
     * 时间格式+随机（重复概率非常低）的字符串
     * @param prefix 前缀
     * @return 字符串
     */
    public static String randomUniqueNumberWithDate(String prefix, Integer length) {
        return prefix.concat(DateTimeUtil.formatLocalDateTimeToString(LocalDateTime.now(), "yyyyMMdd")).concat(RandomStringUtils.random(length, key));
    }


    /**
     * 生成UUID字符串
     * @return UUID字符串
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
