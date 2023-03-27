package com.bosen.common.util;

/**
 * 上下文租户获取工具类
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/27
 */
public class TenantContext {
    private static final ThreadLocal<String> currentTenant = new ThreadLocal<String>();

    public TenantContext() {
    }

    // 在登录的时候向当前线程中存放租户标识
    public static void setTenant(String tenant) {
        currentTenant.set(tenant);
    }

    // 从当前线程取租户标识
    public static String getTenant() {
        return currentTenant.get();
    }

    // 从当前线程删除租户标识
    public static void clear() {
        currentTenant.remove();
    }
}
