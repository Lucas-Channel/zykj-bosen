package com.bosen.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosen.admin.domain.SystemRolePermissionRelation;
import com.bosen.admin.vo.response.RolePermissionDetailVO;
import com.bosen.admin.vo.response.RolesInterfaceUrlVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/25
 */
public interface SystemRolePermissionRelationMapper extends BaseMapper<SystemRolePermissionRelation> {
    List<RolesInterfaceUrlVO> initRolesInterfaceUrlMap();

    List<RolePermissionDetailVO> getRolePermissionList(@Param("roleId") Long roleId, @Param("menuId") Long menuId);

    int deleteByRoleIdAndMenuId(@Param("roleId") Long roleId, @Param("menuId") Long menuId);
}
