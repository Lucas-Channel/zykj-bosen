package com.bosen.auth.security;

import com.bosen.auth.feign.AdminUserFeignService;
import com.bosen.common.constant.response.ResponseCode;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.domain.api.ClientDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * OAuth2 客户端信息
 */
@Service
@RequiredArgsConstructor
public class ClientDetailsServiceImpl implements ClientDetailsService {

    private final AdminUserFeignService adminUserFeignService;

    @Override
//    @Cacheable(cacheNames = "auth", key = "'oauth-client:'+#clientId")
    public ClientDetails loadClientByClientId(String clientId) {
        try {
            // 通过clientId获取getOAuth2ClientById
            ResponseData<ClientDetail> result = adminUserFeignService.getByClientId(clientId);
            if (Objects.equals(result.getCode(), ResponseCode.SUCCESS.getCode()) && Objects.nonNull(result.getData())) {
                ClientDetail client = result.getData();
                BaseClientDetails clientDetails = new BaseClientDetails(
                        client.getClientId(),
                        client.getResourceIds(),
                        client.getScope(),
                        client.getAuthorizedGrantTypes(),
                        client.getAuthorities(),
                        client.getWebServerRedirectUri()
                );
                clientDetails.setClientSecret(client.getClientSecret());
                clientDetails.setAccessTokenValiditySeconds(client.getAccessTokenValidity());
                clientDetails.setRefreshTokenValiditySeconds(client.getRefreshTokenValidity());
                return clientDetails;
            } else {
                throw new NoSuchClientException("未找到改客户端id: " + clientId);
            }
        } catch (EmptyResultDataAccessException var4) {
            throw new NoSuchClientException("未找到改客户端id: " + clientId);
        }
    }
}
