package com.bosen.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.admin.domain.SystemMenu;
import com.bosen.admin.vo.response.MenuDetailVO;
import com.bosen.admin.vo.response.MenuTreeNode;
import com.bosen.admin.vo.resquest.MenuQueryVO;
import com.bosen.admin.vo.resquest.MenuUpsertVO;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;

import java.util.List;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/25
 */
public interface ISystemMenuService extends IService<SystemMenu> {

    /**
     * 新增/修改菜单
     * @param menuUpsertVO
     * @return
     */
    ResponseData<Void> upsertMenu(MenuUpsertVO menuUpsertVO);

    /**
     * 分页查询菜单
     * @param menuQueryVO
     * @return
     */
    ResponseData<PageData<MenuDetailVO>> listPage(MenuQueryVO menuQueryVO);

    /**
     * 树形结构返回所有菜单列表
     * @return
     */
    ResponseData<List<MenuTreeNode>> treeList();
}
