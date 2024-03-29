package com.bosen.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.admin.domain.AdminUserRoleRelation;
import com.bosen.admin.domain.BsRole;
import com.bosen.common.constant.response.ResponseData;

import java.util.List;

/**
 * 后台管理用户-用户角色关联
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/25
 */
public interface ISystemUserRoleRelationService extends IService<AdminUserRoleRelation> {
    /**
     * 通过用户id查询角色列表
     * @param adminUserId
     * @return
     */
    List<BsRole> getUserRoleList(String adminUserId);

    ResponseData<Void> updateUserRole(String adminId, List<String> roleIds);
}
