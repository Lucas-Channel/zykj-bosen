package com.bosen.admin.api.fallback;

import com.bosen.admin.api.feign.AdminFeignService;
import com.bosen.common.constant.response.ResponseCode;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.domain.UserDto;
import com.bosen.common.domain.api.ClientDetail;
import com.bosen.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/25
 */
@Slf4j
@Component
public class AdminFeignServiceFallback implements AdminFeignService {
    @Override
    public UserDto loadUserByUsername(String username) {
        throw new BusinessException(ResponseCode.ADMIN_SERVICE_ERROR);
    }

    @Override
    public ResponseData<ClientDetail> getByClientId(String clientId) {
        throw new BusinessException(ResponseCode.ADMIN_SERVICE_ERROR);
    }

    @Override
    public UserDto loginMerchantByMobile(String mobile) {
        throw new BusinessException(ResponseCode.ADMIN_SERVICE_ERROR);
    }

    @Override
    public void cacheAdminInfo(String adminId) {
        throw new BusinessException(ResponseCode.ADMIN_SERVICE_ERROR);
    }

    @Override
    public void cacheMerchantInfo(String merchantId) {
        throw new BusinessException(ResponseCode.ADMIN_SERVICE_ERROR);
    }
}
