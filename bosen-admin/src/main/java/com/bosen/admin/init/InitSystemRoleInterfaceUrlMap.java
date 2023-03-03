package com.bosen.admin.init;

import com.bosen.admin.service.ISystemRolePermissionRelationService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 初始化后台，角色接口访问权限map
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/25
 */
@Component
public class InitSystemRoleInterfaceUrlMap {

    @Resource
    private ISystemRolePermissionRelationService rolePermissionRelationService;

    @PostConstruct
    public void initRolesInterfaceUrlMap(){
        rolePermissionRelationService.initRolesInterfaceUrlMap();
    }
}

