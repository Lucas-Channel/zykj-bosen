package com.bosen.common.service;


import com.bosen.common.domain.MemberCacheVO;

/**
 * 会员缓存操作类
 */
public interface IMemberCacheService {
    /**
     * 删除后台用户缓存
     */
    void delMember(Long memberId);

    /**
     * 获取缓存会员信息
     */
    MemberCacheVO getMember(Long memberId);

    /**
     * 设置缓存后台用户信息
     */
    void setMember(MemberCacheVO memberCacheVO);
}
