package com.bosen.auth.controller;

import cn.hutool.json.JSONUtil;
import com.bosen.auth.domain.Oauth2TokenDto;
import com.bosen.auth.event.CacheLoginUserInfoEvent;
import com.bosen.auth.vo.LoginVO;
import com.bosen.common.constant.auth.AuthConstant;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.domain.UserDto;
import com.bosen.common.exception.BusinessException;
import com.bosen.common.util.Aes128Util;
import com.nimbusds.jose.JWSObject;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义Oauth2获取令牌接口
 */
@RestController
@RequestMapping("/oauth")
public class AuthController {

    @Resource
    private TokenEndpoint tokenEndpoint;

    @Resource
    private RedisTokenStore redisTokenStore;

    @Resource
    private ApplicationContext applicationContext;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseData<Oauth2TokenDto> postAccessToken(@RequestBody LoginVO loginVO) throws HttpRequestMethodNotSupportedException {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("grant_type",loginVO.getGrant_type());
        parameters.put("client_id",loginVO.getClient_id());
        parameters.put("client_secret",loginVO.getClient_secret());
        parameters.putIfAbsent("refresh_token",loginVO.getRefresh_token());
        parameters.putIfAbsent("username",loginVO.getUsername());
        try {
            parameters.putIfAbsent("password", Aes128Util.aesDecrypt(loginVO.getPassword()));
        } catch (Exception e) {
            throw new BusinessException("密码格式不对");
        }
        UsernamePasswordAuthenticationToken clientUser = new UsernamePasswordAuthenticationToken(new User(loginVO.getClient_id(), loginVO.getClient_secret(), new ArrayList<>()), null, new ArrayList<>());
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(clientUser, parameters).getBody();
        assert oAuth2AccessToken != null;
        Oauth2TokenDto oauth2TokenDto = Oauth2TokenDto.builder()
                .token(oAuth2AccessToken.getValue())
                .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                .expiresIn(oAuth2AccessToken.getExpiresIn())
                .tokenHead(AuthConstant.JWT_TOKEN_PREFIX).build();
        JWSObject jwsObject;
        try {
            jwsObject = JWSObject.parse(oauth2TokenDto.getToken());
        } catch (ParseException e) {
            throw new BusinessException("转换token失败");
        }
        String userStr = jwsObject.getPayload().toString();
        UserDto userDto = JSONUtil.toBean(userStr, UserDto.class);
        applicationContext.publishEvent(new CacheLoginUserInfoEvent(this, userDto.getId(), loginVO.getClient_id()));
        return ResponseData.success(oauth2TokenDto);
    }

    @PostMapping("/logout")
    public ResponseData<Void> logout(@RequestHeader HttpHeaders headers) {
        // 说明，此处拿不到Authorization 因为在白名单过滤器移除了，且白名单过滤器处代码不能注释，因为全局存在token验证
        String authorization = headers.getFirst(AuthConstant.LOGIN_OUT_TOKEN_KEY.toLowerCase());
        String authorizationRefresh = headers.getFirst(AuthConstant.LOGIN_OUT_REFRESH_TOKEN_KEY.toLowerCase());
        if (StringUtils.hasLength(authorization) && StringUtils.hasLength(authorizationRefresh)) {
            String token = authorization.replace(AuthConstant.JWT_TOKEN_PREFIX, "").trim();
            String refreshToken = authorizationRefresh.replace(AuthConstant.LOGIN_OUT_REFRESH_TOKEN_KEY, "").trim();
            OAuth2AccessToken oAuth2AccessToken = redisTokenStore.readAccessToken(token);
            OAuth2RefreshToken oAuth2RefreshToken = redisTokenStore.readRefreshToken(refreshToken);
            redisTokenStore.removeAccessToken(oAuth2AccessToken);
            redisTokenStore.removeRefreshToken(oAuth2RefreshToken);
            return ResponseData.success();
        }
        return ResponseData.fail();
    }
}
