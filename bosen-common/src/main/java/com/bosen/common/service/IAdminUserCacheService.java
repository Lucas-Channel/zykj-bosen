package com.bosen.common.service;


import com.bosen.common.domain.AdminUserCacheVO;

/**
 * 后台用户缓存操作类
 */
public interface IAdminUserCacheService {
    /**
     * 删除后台用户缓存
     */
    void delAdmin(Long adminId);

    /**
     * 获取缓存后台用户信息
     */
    AdminUserCacheVO getAdmin(Long adminId);

    /**
     * 设置缓存后台用户信息
     */
    void setAdmin(AdminUserCacheVO admin);
}
