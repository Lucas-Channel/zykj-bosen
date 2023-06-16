package com.bosen.auth.controller;

import cn.hutool.json.JSONUtil;
import com.bosen.auth.domain.Oauth2TokenDto;
import com.bosen.auth.event.CacheLoginUserInfoEvent;
import com.bosen.auth.vo.LoginVO;
import com.bosen.common.constant.auth.AuthConstant;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.domain.UserDto;
import com.bosen.common.exception.BusinessException;
import com.nimbusds.jose.JWSObject;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    private ApplicationContext applicationContext;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseData<Oauth2TokenDto> postAccessToken(@RequestBody LoginVO loginVO) throws HttpRequestMethodNotSupportedException {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("grant_type",loginVO.getGrant_type());
        parameters.put("client_id",loginVO.getClient_id());
        parameters.put("client_secret",loginVO.getClient_secret());
        parameters.putIfAbsent("refresh_token",loginVO.getRefresh_token());
        parameters.putIfAbsent("username",loginVO.getUsername());
        parameters.putIfAbsent("password",loginVO.getPassword());
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

//    @DeleteMapping("/logout")
//    public ResponseData<Void> logout() {
//
//        JSONObject payload = JwtUtils.getJwtPayload();
//        String jti = payload.getStr(AuthConstant.JWT_JTI); // JWT唯一标识
//        Long expireTime = payload.getLong(AuthConstant.JWT_EXP); // JWT过期时间戳(单位：秒)
//        if (expireTime != null) {
//            long currentTime = System.currentTimeMillis() / 1000;// 当前时间（单位：秒）
//            if (expireTime > currentTime) { // token未过期，添加至缓存作为黑名单限制访问，缓存时间为token过期剩余时间
//                redisTemplate.opsForValue().set(AuthConstant.TOKEN_BLACKLIST_PREFIX + jti, null, (expireTime - currentTime), TimeUnit.SECONDS);
//            }
//        } else { // token 永不过期则永久加入黑名单
//            redisTemplate.opsForValue().set(AuthConstant.TOKEN_BLACKLIST_PREFIX + jti, null);
//        }
//        return ResponseData.success();
//    }
}
