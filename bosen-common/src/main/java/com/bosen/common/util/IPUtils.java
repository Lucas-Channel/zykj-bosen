package com.bosen.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * ip地址解析
 * @author Lucas
 * @version 2.0.0
 * @date 2023/6/23
 */
public class IPUtils {
    private final static String UNKNOWN = "unknown";

    public static String getRealIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String getRealIpV2(HttpServletRequest request) {
        String accessIp = request.getHeader("x-forwarded-for");
        if (null == accessIp) {
            return request.getRemoteAddr();
        }
        return accessIp;
    }
}
