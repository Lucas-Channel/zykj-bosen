package com.bosen.common.domain.api;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Lucas
 */
@Data
@TableName("bs_oauth_client_details")// 后期统一调整
public class ClientDetail extends BaseEntityData implements Serializable {
    private static final long serialVersionUID = 2908517265351728375L;

    private String clientId;

    private String resourceIds;

    private String clientSecret;// 存储加密后的数据

    private String scope;

    private String authorizedGrantTypes;

    private String webServerRedirectUri;

    private String authorities;

    private Integer accessTokenValidity;

    private Integer refreshTokenValidity;

    private String additionalInformation;

    private String autoapprove;
}
