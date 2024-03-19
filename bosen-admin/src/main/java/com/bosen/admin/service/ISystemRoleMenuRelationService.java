package com.bosen.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.admin.domain.SystemRoleMenuRelation;
import com.bosen.admin.vo.response.LoginMenuDetailVO;
import com.bosen.admin.vo.response.RoleMenuDetailVO;
import com.bosen.admin.vo.resquest.RoleMenuUpsertVO;
import com.bosen.common.constant.response.ResponseData;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/25
 */
public interface ISystemRoleMenuRelationService extends IService<SystemRoleMenuRelation> {

    ResponseData<List<RoleMenuDetailVO>> getRoleMenuList(String roleId);

    ResponseData<Void> updateRoleMenu(RoleMenuUpsertVO roleMenuUpsertVO);

    ResponseData<Map<String, Object>> listMenuAfterLogin(String roleId);
}
