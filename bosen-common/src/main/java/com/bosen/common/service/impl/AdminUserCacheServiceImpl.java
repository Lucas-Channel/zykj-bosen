package com.bosen.common.service.impl;

import cn.hutool.json.JSONUtil;
import com.bosen.common.constant.auth.RedisKeyConstant;
import com.bosen.common.domain.AdminUserCacheVO;
import com.bosen.common.service.IAdminUserCacheService;
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
public class AdminUserCacheServiceImpl implements IAdminUserCacheService {
    @Resource
    private RedisService redisService;

    @Override
    public void delAdmin(Long adminId) {
        log.info("删除登录用户缓存信息，用户id为：{}", adminId);
        String key = RedisKeyConstant.CACHE_ADMIN_USER_KEY + adminId;
        redisService.del(key);
    }

    @Override
    public AdminUserCacheVO getAdmin(Long adminId) {
        log.info("删除登录用户缓存信息，用户id为：{}", adminId);
        String key = RedisKeyConstant.CACHE_ADMIN_USER_KEY + adminId;
        return (AdminUserCacheVO) redisService.get(key);
    }

    @Override
    public void setAdmin(AdminUserCacheVO admin) {
        log.info("添加登录用户缓存信息，用户信息为：{}", JSONUtil.toJsonStr(admin));
        String key = RedisKeyConstant.CACHE_ADMIN_USER_KEY + admin.getId();
        redisService.set(key, admin, RedisKeyConstant.CACHE_ADMIN_USER_EXPIRE);
    }
}
