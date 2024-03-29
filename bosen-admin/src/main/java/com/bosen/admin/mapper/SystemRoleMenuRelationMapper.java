package com.bosen.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosen.admin.domain.SystemRoleMenuRelation;
import com.bosen.admin.vo.response.RoleMenuDetailVO;

import java.util.List;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/25
 */
public interface SystemRoleMenuRelationMapper extends BaseMapper<SystemRoleMenuRelation> {
    List<RoleMenuDetailVO> getRoleMenuList(String roleId);
}
