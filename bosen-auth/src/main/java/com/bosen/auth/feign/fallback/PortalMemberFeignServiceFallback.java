package com.bosen.auth.feign.fallback;

import com.bosen.auth.feign.PortalMemberFeignService;
import com.bosen.common.constant.response.ResponseCode;
import com.bosen.common.domain.UserDto;
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
public class PortalMemberFeignServiceFallback implements PortalMemberFeignService {
    @Override
    public UserDto loadUserByUsername(String username) {
        throw new BusinessException(ResponseCode.ADMIN_SERVICE_ERROR);
    }

    @Override
    public void cacheMemberInfo(Long memberId) {
        throw new BusinessException(ResponseCode.ADMIN_SERVICE_ERROR);
    }
}
