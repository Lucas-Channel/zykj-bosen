package com.bosen.common.service;

import com.bosen.common.domain.BsUserAccountCacheVO;

/**
 * 用户账户表(BsUserAccount)缓存操作类
 *
 * @author gaofeicm
 * @since 2023-03-30 22:27:07
 */
public interface IBsUserAccountCacheService {
    /**
     * 删除后台用户缓存
     */
    void delAdmin(String adminId);

    /**
     * 获取缓存后台用户信息
     */
    BsUserAccountCacheVO getAdmin(String adminId);

    /**
     * 设置缓存后台用户信息
     */
    void setAdmin(BsUserAccountCacheVO admin);
}
