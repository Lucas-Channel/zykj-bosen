package com.bosen.common.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONObject;
import com.bosen.common.constant.auth.AuthConstant;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Lucas, 当前登录信息
 */
@Slf4j
public class AuthUser {

    public static Long getUserId() {
        Long userId = null;
        JSONObject jsonObject = JwtUtils.getJwtPayload();
        if (!Objects.isNull(jsonObject)) {
            userId = jsonObject.getLong("userId");
        }
        return userId;
    }

    public static String getUsername() {
        return JwtUtils.getJwtPayload().getStr("user_name");
    }

    public static List<String> getRoles() {
        List<String> roles;
        JSONObject payload =  JwtUtils.getJwtPayload();
        if (payload.containsKey(AuthConstant.AUTHORITY_CLAIM_NAME)) {
            roles = payload.getJSONArray(AuthConstant.AUTHORITY_CLAIM_NAME).toList(String.class);
        } else {
            roles = Collections.emptyList();
        }
        return roles;
    }

    public static boolean isAdmin() {
        List<String> roles = getRoles();
        return CollectionUtil.isNotEmpty(roles) && roles.contains("ADMIN");
    }
}
