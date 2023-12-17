package com.bosen.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.admin.bo.ViewFrontMenuDetailBO;
import com.bosen.admin.domain.SystemRoleMenuRelation;
import com.bosen.admin.mapper.SystemMenuMapper;
import com.bosen.admin.mapper.SystemRoleMenuRelationMapper;
import com.bosen.admin.service.ISystemRoleMenuRelationService;
import com.bosen.admin.vo.response.LoginMenuDetailVO;
import com.bosen.admin.vo.response.LoginMenuMetaDetailVO;
import com.bosen.admin.vo.response.RoleMenuDetailVO;
import com.bosen.admin.vo.resquest.RoleMenuUpsertVO;
import com.bosen.common.constant.response.ResponseCode;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/25
 */
@Slf4j
@Service
public class SystemRoleMenuRelationServiceImpl extends ServiceImpl<SystemRoleMenuRelationMapper, SystemRoleMenuRelation> implements ISystemRoleMenuRelationService {

    @Resource
    private SystemMenuMapper systemMenuMapper;

    @Override
    public ResponseData<List<RoleMenuDetailVO>> getRoleMenuList(String roleId) {
        List<RoleMenuDetailVO> roleMenuList = baseMapper.getRoleMenuList(roleId);
        List<RoleMenuDetailVO> list = roleMenuList.stream()
                .filter(menu -> Objects.equals("0", menu.getParentId()))
                .map(menu -> covertRoleMenuNode(menu, roleMenuList)).collect(Collectors.toList());
        return ResponseData.success(list);
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public ResponseData<Void> updateRoleMenu(RoleMenuUpsertVO roleMenuUpsertVO) {
        // 删除历史
        boolean remove = this.lambdaUpdate().eq(SystemRoleMenuRelation::getRoleId, roleMenuUpsertVO.getRoleId()).remove();
        if (!remove) {
            throw new BusinessException(ResponseCode.UPDATE_ROLE_MENU_PERMISSION_ERROR);
        }
        List<SystemRoleMenuRelation> roleMenuRelations = roleMenuUpsertVO.getMenuIds().stream().map(i -> {
            SystemRoleMenuRelation roleMenuRelation = new SystemRoleMenuRelation();
            roleMenuRelation.setMenuId(i);
            roleMenuRelation.setRoleId(roleMenuUpsertVO.getRoleId());
            return roleMenuRelation;
        }).collect(Collectors.toList());
        return ResponseData.judge(this.saveBatch(roleMenuRelations));
    }

    @Override
    public ResponseData<List<LoginMenuDetailVO>> listMenuAfterLogin(String roleId) {
        // todo 获取当前用户角色
        // 获取角色下的菜单
        List<SystemRoleMenuRelation> list = this.lambdaQuery().eq(SystemRoleMenuRelation::getRoleId, roleId).list();
        if (CollectionUtils.isEmpty(list)) {
            throw new BusinessException(ResponseCode.FORBIDDEN);
        }
        // todo 获取请求头的多语言编码
        List<ViewFrontMenuDetailBO> menuDetailBOS = systemMenuMapper.listMenuByMenuIds(list.stream().map(SystemRoleMenuRelation::getMenuId).distinct().collect(Collectors.toList()), "zh");
        return ResponseData.success(menuDetailBOS.stream().filter(i -> Objects.equals("0", i.getParentId()))
                .map(i -> covertRoleMenuNodeForLogin(i, menuDetailBOS)).collect(Collectors.toList()));
    }

    private RoleMenuDetailVO covertRoleMenuNode(RoleMenuDetailVO menu, List<RoleMenuDetailVO> menuList) {
        RoleMenuDetailVO node = new RoleMenuDetailVO();
        BeanUtils.copyProperties(menu, node);
        List<RoleMenuDetailVO> children = menuList.stream()
                .filter(subMenu -> subMenu.getParentId().equals(menu.getMenuId()))
                .map(subMenu -> covertRoleMenuNode(subMenu, menuList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }

    private LoginMenuDetailVO covertRoleMenuNodeForLogin(ViewFrontMenuDetailBO menu, List<ViewFrontMenuDetailBO> menuList) {
        LoginMenuDetailVO node = new LoginMenuDetailVO();
        BeanUtils.copyProperties(menu, node);
        LoginMenuMetaDetailVO meta = new LoginMenuMetaDetailVO();
        meta.setIcon(menu.getIcon());
        meta.setOrder(menu.getSortNumber());
        meta.setTitle(menu.getTitle());
        meta.setI18nTitle(menu.getI18nTitle());
        node.setMeta(meta);
        List<LoginMenuDetailVO> children = menuList.stream()
                .filter(subMenu -> subMenu.getParentId().equals(menu.getId()))
                .map(subMenu -> covertRoleMenuNodeForLogin(subMenu, menuList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }
}
