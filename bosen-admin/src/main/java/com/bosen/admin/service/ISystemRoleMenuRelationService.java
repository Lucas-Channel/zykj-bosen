package com.bosen.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.admin.domain.SystemRoleMenuRelation;
import com.bosen.admin.vo.response.LoginMenuDetailVO;
import com.bosen.admin.vo.response.RoleMenuDetailVO;
import com.bosen.admin.vo.resquest.RoleMenuUpsertVO;
import com.bosen.common.constant.response.ResponseData;

import java.util.List;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/25
 */
public interface ISystemRoleMenuRelationService extends IService<SystemRoleMenuRelation> {

    ResponseData<List<RoleMenuDetailVO>> getRoleMenuList(String roleId);

    ResponseData<Void> updateRoleMenu(RoleMenuUpsertVO roleMenuUpsertVO);

    ResponseData<List<LoginMenuDetailVO>> listMenuAfterLogin(String roleId);
}
