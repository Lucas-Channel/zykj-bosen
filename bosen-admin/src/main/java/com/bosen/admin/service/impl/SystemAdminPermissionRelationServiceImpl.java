package com.bosen.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.admin.domain.SystemAdminPermissionRelation;
import com.bosen.admin.mapper.SystemAdminPermissionRelationMapper;
import com.bosen.admin.service.ISystemAdminPermissionRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 后台用户和权限关系表(除角色中定义的权限以外的加减权限)
 */
@Slf4j
@Service
public class SystemAdminPermissionRelationServiceImpl extends ServiceImpl<SystemAdminPermissionRelationMapper, SystemAdminPermissionRelation> implements ISystemAdminPermissionRelationService {

}