package com.bosen.auth.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Oauth2获取Token返回信息封装
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class Oauth2TokenDto {
    private String token;

    private String refreshToken;

    private String tokenHead;

    private Integer expiresIn;
}
