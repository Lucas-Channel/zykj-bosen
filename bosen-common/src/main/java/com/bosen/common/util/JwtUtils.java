package com.bosen.common.util;

import cn.hutool.json.JSONObject;
import com.bosen.common.constant.auth.AuthConstant;
import com.bosen.common.exception.BusinessException;
import com.nimbusds.jose.JWSObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.text.ParseException;
import java.util.Objects;

/**
 * JWT工具类
 * @author Lucas
 */
@Slf4j
public class JwtUtils {

    public static JSONObject getJwtPayload() {
        String token = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest().getHeader(AuthConstant.JWT_PAYLOAD_KEY);
        if (!StringUtils.hasLength(token)) {
            throw new BusinessException("token不能为空");
        }
        String realToken = token.replace(AuthConstant.JWT_TOKEN_PREFIX, "");
        JWSObject jwsObject = null;
        try {
            jwsObject = JWSObject.parse(realToken);
        } catch (ParseException e) {
            throw new BusinessException("token转换异常");
        }
        String userStr = jwsObject.getPayload().toString();
        return new JSONObject(userStr);
    }
}
