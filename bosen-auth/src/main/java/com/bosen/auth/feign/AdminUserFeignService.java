package com.bosen.auth.feign;

import com.bosen.auth.feign.fallback.AdminUserFeignServiceFallback;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.domain.UserDto;
import com.bosen.common.domain.api.ClientDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 管理端用户对内api
 */
@FeignClient(value = "bosen-admin", fallback = AdminUserFeignServiceFallback.class)
public interface AdminUserFeignService {

    @GetMapping("/admin/loadByUsername")
    UserDto loadUserByUsername(@RequestParam String username);

    @GetMapping("/client/getByClientId")
    ResponseData<ClientDetail> getByClientId(@RequestParam String clientId);

    @GetMapping("/merchant/loginByMobile")
    UserDto loginMerchantByMobile(@RequestParam String mobile);

    @PostMapping("/admin/cacheAdminInfo")
    void cacheAdminInfo(@RequestBody Long adminId);

    @PostMapping("/merchant/cacheMerchantInfo")
    void cacheMerchantInfo(@RequestBody Long merchantId);
}
