package com.bosen.common.service;


import com.bosen.common.domain.MerchantCacheVO;

/**
 * 商户后台用户缓存操作类
 */
public interface IMerchantCacheService {
    /**
     * 删除后台用户缓存
     */
    void delMerchant(Long merchantId);

    /**
     * 获取缓存后台用户信息
     */
    MerchantCacheVO getMerchant(Long merchantId);

    /**
     * 设置缓存后台用户信息
     */
    void setMerchant(MerchantCacheVO merchantCacheVO);
}
