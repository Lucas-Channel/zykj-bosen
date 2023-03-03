package com.bosen.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.admin.domain.SystemRole;
import com.bosen.admin.vo.response.RoleDetailVO;
import com.bosen.admin.vo.resquest.RoleQueryVO;
import com.bosen.admin.vo.resquest.RoleUpsertVO;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/25
 */
public interface ISystemRoleService extends IService<SystemRole> {
    ResponseData<PageData<RoleDetailVO>> getRolePageList(RoleQueryVO queryVO);

    ResponseData<Void> upsertRole(RoleUpsertVO role);
}
