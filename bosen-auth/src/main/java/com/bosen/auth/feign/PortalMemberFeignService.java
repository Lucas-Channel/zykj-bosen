package com.bosen.auth.feign;

import com.bosen.auth.feign.fallback.PortalMemberFeignServiceFallback;
import com.bosen.common.domain.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * c端用户对内api
 */
@FeignClient(value = "bosen-member", fallback = PortalMemberFeignServiceFallback.class)
public interface PortalMemberFeignService {
    @GetMapping("/member/loadByUsername")
    UserDto loadUserByUsername(@RequestParam String username);

    @PostMapping("/member/cacheMemberInfo")
    void cacheMemberInfo(@RequestBody String memberId);
}
