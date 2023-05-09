package com.bosen.common.service.impl;

import cn.hutool.json.JSONUtil;
import com.bosen.common.constant.common.RedisKeyConstant;
import com.bosen.common.domain.MerchantCacheVO;
import com.bosen.common.service.IMerchantCacheService;
import com.bosen.common.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 后台用户缓存操作类
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/27
 */
@Slf4j
@Service
public class MerchantCacheServiceImpl implements IMerchantCacheService {
    @Resource
    private RedisService redisService;

    @Override
    public void delMerchant(Long merchantId) {
        log.info("删除登录用户缓存信息，用户id为：{}", merchantId);
        String key = RedisKeyConstant.CACHE_ADMIN_MERCHANT_KEY + merchantId;
        redisService.del(key);
    }

    @Override
    public MerchantCacheVO getMerchant(Long merchantId) {
        log.info("删除登录用户缓存信息，用户id为：{}", merchantId);
        String key = RedisKeyConstant.CACHE_ADMIN_MERCHANT_KEY + merchantId;
        return (MerchantCacheVO) redisService.get(key);
    }

    @Override
    public void setMerchant(MerchantCacheVO admin) {
        log.info("添加登录用户缓存信息，用户信息为：{}", JSONUtil.toJsonStr(admin));
        String key = RedisKeyConstant.CACHE_ADMIN_MERCHANT_KEY + admin.getId();
        redisService.set(key, admin, RedisKeyConstant.CACHE_ADMIN_USER_EXPIRE);
    }
}
