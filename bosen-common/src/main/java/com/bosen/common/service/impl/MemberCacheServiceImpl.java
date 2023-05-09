package com.bosen.common.service.impl;

import cn.hutool.json.JSONUtil;
import com.bosen.common.constant.common.RedisKeyConstant;
import com.bosen.common.domain.MemberCacheVO;
import com.bosen.common.service.IMemberCacheService;
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
public class MemberCacheServiceImpl implements IMemberCacheService {
    @Resource
    private RedisService redisService;

    @Override
    public void delMember(Long memberId) {
        log.info("删除登录用户缓存信息，用户id为：{}", memberId);
        String key = RedisKeyConstant.CACHE_MEMBER_KEY + memberId;
        redisService.del(key);
    }

    @Override
    public MemberCacheVO getMember(Long memberId) {
        log.info("删除登录用户缓存信息，会员id为：{}", memberId);
        String key = RedisKeyConstant.CACHE_MEMBER_KEY + memberId;
        return (MemberCacheVO) redisService.get(key);
    }

    @Override
    public void setMember(MemberCacheVO memberCacheVO) {
        log.info("添加登录会员缓存信息，用户信息为：{}", JSONUtil.toJsonStr(memberCacheVO));
        String key = RedisKeyConstant.CACHE_MEMBER_KEY + memberCacheVO.getId();
        redisService.set(key, memberCacheVO, RedisKeyConstant.CACHE_ADMIN_USER_EXPIRE);
    }
}
