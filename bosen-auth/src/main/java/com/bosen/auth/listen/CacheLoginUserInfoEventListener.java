package com.bosen.auth.listen;

import com.bosen.admin.api.feign.AdminFeignService;
import com.bosen.auth.event.CacheLoginUserInfoEvent;
import com.bosen.auth.feign.PortalMemberFeignService;
import com.bosen.common.constant.auth.AuthConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/23
 */
@Slf4j
@Component
public class CacheLoginUserInfoEventListener {

    @Resource
    private AdminFeignService adminFeignService;

    @Resource
    private PortalMemberFeignService memberFeignService;

    @EventListener
    public void cacheInfo(CacheLoginUserInfoEvent cacheLoginUserInfoEvent) {
        log.info("进来了，监听器");
        if (AuthConstant.ADMIN_CLIENT_ID.equals(cacheLoginUserInfoEvent.getClientId())) {
            adminFeignService.cacheAdminInfo(cacheLoginUserInfoEvent.getId());
        } else if (AuthConstant.MERCHANT_CLIENT_ID.equals(cacheLoginUserInfoEvent.getClientId())) {
            adminFeignService.cacheMerchantInfo(cacheLoginUserInfoEvent.getId());
        } else {
            memberFeignService.cacheMemberInfo(cacheLoginUserInfoEvent.getId());
        }
    }
}
