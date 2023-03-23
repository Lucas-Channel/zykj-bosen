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

    /**
     * 缓存c端用户信息key
     */
    String CACHE_MEMBER_KEY = "bosen:member:user:";

    /**
     * 缓存商户后台用户信息key
     */
    String CACHE_ADMIN_MERCHANT_KEY = "bosen:admin:merchant:";

    String TOKEN_KEY = "bs-auth-token:";
}
