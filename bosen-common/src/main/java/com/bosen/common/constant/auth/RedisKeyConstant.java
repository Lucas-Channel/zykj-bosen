package com.bosen.common.constant.auth;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/27
 */
public interface RedisKeyConstant {
    /**
     * 缓存后台用户信息有效期
     */
    Long CACHE_ADMIN_USER_EXPIRE = 86400L;

    /**
     * 缓存后台用户信息key
     */
    String CACHE_ADMIN_USER_KEY = "bosen:admin:user:";
}
