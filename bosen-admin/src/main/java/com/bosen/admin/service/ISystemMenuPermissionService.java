package com.bosen.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.admin.domain.SystemMenuPermissionDO;
import com.bosen.admin.vo.response.SystemMenuPermissionDetail;
import com.bosen.admin.vo.resquest.MenuPermissionQueryVO;
import com.bosen.admin.vo.resquest.MenuPermissionUpsertVO;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/25
 */
public interface ISystemMenuPermissionService extends IService<SystemMenuPermissionDO> {
    /**
     * 分页
     * @param query
     * @return
     */
    ResponseData<PageData<SystemMenuPermissionDetail>> listPermissionsWithPage(MenuPermissionQueryVO query);

    /**
     * 新增/修改权限
     * @param permission
     * @return
     */
    ResponseData<Void> upsertPermission(MenuPermissionUpsertVO permission);
}
