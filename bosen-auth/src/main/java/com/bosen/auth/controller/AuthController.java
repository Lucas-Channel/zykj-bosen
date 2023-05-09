package com.bosen.auth.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.bosen.auth.domain.Oauth2TokenDto;
import com.bosen.auth.event.CacheLoginUserInfoEvent;
import com.bosen.common.constant.auth.AuthConstant;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.domain.UserDto;
import com.bosen.common.exception.BusinessException;
import com.bosen.common.util.JwtUtils;
import com.nimbusds.jose.JWSObject;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 自定义Oauth2获取令牌接口
 */
@RestController
@RequestMapping("/oauth")
public class AuthController {

    @Resource
    private TokenEndpoint tokenEndpoint;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private ApplicationContext applicationContext;

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseData<Oauth2TokenDto> postAccessToken(HttpServletRequest request,
                                                         @RequestParam String grant_type,
                                                         @RequestParam String client_id,
                                                         @RequestParam String client_secret,
                                                         @RequestParam(required = false) String refresh_token,
                                                         @RequestParam(required = false) String username,
                                                         @RequestParam(required = false) String password) throws HttpRequestMethodNotSupportedException {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("grant_type",grant_type);
        parameters.put("client_id",client_id);
        parameters.put("client_secret",client_secret);
        parameters.putIfAbsent("refresh_token",refresh_token);
        parameters.putIfAbsent("username",username);
        parameters.putIfAbsent("password",password);
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(request.getUserPrincipal(), parameters).getBody();
        Oauth2TokenDto oauth2TokenDto = Oauth2TokenDto.builder()
                .token(oAuth2AccessToken.getValue())
                .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                .expiresIn(oAuth2AccessToken.getExpiresIn())
                .tokenHead(AuthConstant.JWT_TOKEN_PREFIX).build();
        JWSObject jwsObject = null;
        try {
            jwsObject = JWSObject.parse(oauth2TokenDto.getToken());
        } catch (ParseException e) {
            throw new BusinessException("转换token失败");
        }
        String userStr = jwsObject.getPayload().toString();
        UserDto userDto = JSONUtil.toBean(userStr, UserDto.class);
        applicationContext.publishEvent(new CacheLoginUserInfoEvent(this, userDto.getId(), client_id));
        return ResponseData.success(oauth2TokenDto);
    }

    @DeleteMapping("/logout")
    public ResponseData<Void> logout() {

        JSONObject payload = JwtUtils.getJwtPayload();
        String jti = payload.getStr(AuthConstant.JWT_JTI); // JWT唯一标识
        Long expireTime = payload.getLong(AuthConstant.JWT_EXP); // JWT过期时间戳(单位：秒)
        if (expireTime != null) {
            long currentTime = System.currentTimeMillis() / 1000;// 当前时间（单位：秒）
            if (expireTime > currentTime) { // token未过期，添加至缓存作为黑名单限制访问，缓存时间为token过期剩余时间
                redisTemplate.opsForValue().set(AuthConstant.TOKEN_BLACKLIST_PREFIX + jti, null, (expireTime - currentTime), TimeUnit.SECONDS);
            }
        } else { // token 永不过期则永久加入黑名单
            redisTemplate.opsForValue().set(AuthConstant.TOKEN_BLACKLIST_PREFIX + jti, null);
        }
        return ResponseData.success("注销成功");
    }
}
