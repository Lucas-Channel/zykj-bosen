package com.bosen.admin.controller;

import com.bosen.admin.domain.SystemMenuDO;
import com.bosen.admin.service.ISystemMenuService;
import com.bosen.admin.vo.response.MenuDetailVO;
import com.bosen.admin.vo.response.MenuTreeNode;
import com.bosen.admin.vo.resquest.MenuQueryVO;
import com.bosen.admin.vo.resquest.MenuUpsertVO;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 后台菜单管理
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/26
 */
@RestController
@RequestMapping("/menu")
public class SystemMenuController {

    @Resource
    private ISystemMenuService systemDynamicMenuService;

    /**
     * 新增修改菜单
     * @return 结果
     */
    @PostMapping("/upsertMenu")
    public ResponseData<Void> upsertMenu(@RequestBody @Valid MenuUpsertVO menuUpsertVO) {
        return systemDynamicMenuService.upsertMenu(menuUpsertVO);
    }

    /**
     * 根据ID获取菜单详情
     * @param id id
     * @return 结果
     */
    @GetMapping("/detail/{id}")
    public ResponseData<MenuDetailVO> getItem(@PathVariable String id) {
        SystemMenuDO menu = systemDynamicMenuService.getById(id);
        MenuDetailVO menuDetailVO = new MenuDetailVO();
        BeanUtils.copyProperties(menu, menuDetailVO);
        return ResponseData.success(menuDetailVO);
    }

    /**
     * 根据ID删除后台菜单
     * @param id id
     * @return 参数
     */
    @DeleteMapping("/delete/{id}")
    public ResponseData<Void> delete(@PathVariable String id) {
        return ResponseData.judge(systemDynamicMenuService.removeById(id));
    }

    /**
     * 分页查询后台菜单
     * @param menuQueryVO 参数
     * @return 结果
     */
    @GetMapping("/listPage")
    public ResponseData<PageData<MenuDetailVO>> listPage(MenuQueryVO menuQueryVO) {
        return systemDynamicMenuService.listPage(menuQueryVO);
    }

    /**
     * 树形结构返回所有菜单列表
     * @return 结果
     */
    @RequestMapping(value = "/treeList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<List<MenuTreeNode>> treeList() {
        return systemDynamicMenuService.treeList();
    }

}
