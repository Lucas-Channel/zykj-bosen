package com.bosen.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.admin.domain.SystemRolePermissionRelation;
import com.bosen.admin.mapper.SystemRolePermissionRelationMapper;
import com.bosen.admin.service.ISystemRolePermissionRelationService;
import com.bosen.admin.vo.response.RolePermissionDetailVO;
import com.bosen.admin.vo.response.RolesInterfaceUrlVO;
import com.bosen.admin.vo.resquest.RolePermissionUpsertVO;
import com.bosen.common.constant.auth.AuthConstant;
import com.bosen.common.constant.response.ResponseCode;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.exception.BusinessException;
import com.bosen.common.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/25
 */
@Slf4j
@Service
public class SystemRolePermissionRelationServiceImpl extends ServiceImpl<SystemRolePermissionRelationMapper, SystemRolePermissionRelation> implements ISystemRolePermissionRelationService {

    @Value("${spring.application.name}")
    private String applicationName;

    @Resource
    private RedisService redisService;

    @Override
    public void initRolesInterfaceUrlMap() {
        Map<String,List<String>> roleMap = new TreeMap<>();
        List<RolesInterfaceUrlVO> list = this.baseMapper.initRolesInterfaceUrlMap();
        if (CollUtil.isNotEmpty(list)) {
            Map<String, List<RolesInterfaceUrlVO>> collect = list.stream().collect(Collectors.groupingBy(RolesInterfaceUrlVO::getInterfaceUrl));
            collect.forEach((k, v)-> {
                List<String> stringList = v.stream().map(RolesInterfaceUrlVO::getRoleName).collect(Collectors.toList());
                roleMap.put("/" + applicationName + k, stringList);
            });
            redisService.del(AuthConstant.RESOURCE_ROLES_MAP_KEY);
            redisService.hSetAll(AuthConstant.RESOURCE_ROLES_MAP_KEY, roleMap);
        }
    }

    @Override
    public ResponseData<List<RolePermissionDetailVO>> getRolePermissionList(Long roleId, Long menuId) {
        return ResponseData.success(baseMapper.getRolePermissionList(roleId, menuId));
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public ResponseData<Void> updateRolePermission(RolePermissionUpsertVO rolePermissionUpsertVO) {
        // 删除历史权限
        int st = baseMapper.deleteByRoleIdAndMenuId(rolePermissionUpsertVO.getRoleId(), rolePermissionUpsertVO.getMenuId());
        if (0 > st) {
            throw new BusinessException(ResponseCode.UPDATE_ROLE_MENU_PERMISSION_ERROR);
        }
        // 保存当前权限
        List<SystemRolePermissionRelation> rolePermissionRelations = rolePermissionUpsertVO.getPermissionIds().stream().map(i -> {
            SystemRolePermissionRelation rolePermissionRelation = new SystemRolePermissionRelation();
            rolePermissionRelation.setPermissionId(i);
            rolePermissionRelation.setRoleId(rolePermissionUpsertVO.getRoleId());
            return rolePermissionRelation;
        }).collect(Collectors.toList());
        return ResponseData.judge(this.saveBatch(rolePermissionRelations));
    }


}
