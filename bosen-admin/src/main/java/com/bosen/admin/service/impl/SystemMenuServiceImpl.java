package com.bosen.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.admin.domain.SystemMenuDO;
import com.bosen.admin.mapper.SystemMenuMapper;
import com.bosen.admin.service.ISystemMenuService;
import com.bosen.admin.vo.response.MenuDetailVO;
import com.bosen.admin.vo.response.MenuTreeNode;
import com.bosen.admin.vo.resquest.MenuQueryVO;
import com.bosen.admin.vo.resquest.MenuUpsertVO;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SystemMenuServiceImpl extends ServiceImpl<SystemMenuMapper, SystemMenuDO> implements ISystemMenuService {
    @Override
    public ResponseData<Void> upsertMenu(MenuUpsertVO menuUpsertVO) {
        SystemMenuDO menu = new SystemMenuDO();
        BeanUtils.copyProperties(menuUpsertVO, menu);
        if (Objects.isNull(menuUpsertVO.getId())) {
            menu.setCreateTime(LocalDateTime.now());
        }
        return ResponseData.judge(this.saveOrUpdate(menu));
    }

    @Override
    public ResponseData<PageData<MenuDetailVO>> listPage(MenuQueryVO menuQueryVO) {
        Page<SystemMenuDO> page = this.page(new Page<>(menuQueryVO.getCurrent(), menuQueryVO.getSize()), new LambdaQueryWrapper<SystemMenuDO>()
                .like(StringUtils.hasLength(menuQueryVO.getName()), SystemMenuDO::getTitle, menuQueryVO.getName())
                .orderByDesc(SystemMenuDO::getSortNumber));
        return ResponseData.success(new PageData<>(page.getTotal(), page.getRecords().stream().map(i -> {
            MenuDetailVO menuDetailVO = new MenuDetailVO();
            BeanUtils.copyProperties(i, menuDetailVO);
            return menuDetailVO;
        }).collect(Collectors.toList())));
    }

    @Override
    public ResponseData<List<MenuTreeNode>> treeList() {
        List<SystemMenuDO> menuList = this.list();
        List<MenuTreeNode> result = menuList.stream()
                .filter(menu -> menu.getParentId().equals(0L))
                .map(menu -> covertMenuNode(menu, menuList)).collect(Collectors.toList());
        return ResponseData.success(result);
    }

    /**
     * 将Menu转化为MenuNode并设置children属性
     */
    private MenuTreeNode covertMenuNode(SystemMenuDO menu, List<SystemMenuDO> menuList) {
        MenuTreeNode node = new MenuTreeNode();
        BeanUtils.copyProperties(menu, node);
        List<MenuTreeNode> children = menuList.stream()
                .filter(subMenu -> subMenu.getParentId().equals(menu.getId()))
                .map(subMenu -> covertMenuNode(subMenu, menuList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }
}
