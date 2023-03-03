package com.bosen.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosen.admin.domain.SystemRole;
import com.bosen.admin.domain.SystemUserRoleRelation;

import java.util.List;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/25
 */
public interface SystemUserRoleRelationMapper extends BaseMapper<SystemUserRoleRelation> {
    /**
     * 通过用户id查询角色列表
     * @param adminUserId
     * @return
     */
    List<SystemRole> getUserRoleList(Long adminUserId);
}
