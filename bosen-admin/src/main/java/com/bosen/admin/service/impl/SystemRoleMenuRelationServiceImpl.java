package com.bosen.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.admin.domain.SystemRoleMenuRelation;
import com.bosen.admin.mapper.SystemRoleMenuRelationMapper;
import com.bosen.admin.service.ISystemRoleMenuRelationService;
import com.bosen.admin.vo.response.LoginMenuDetailVO;
import com.bosen.admin.vo.response.RoleMenuDetailVO;
import com.bosen.admin.vo.resquest.RoleMenuUpsertVO;
import com.bosen.common.constant.response.ResponseCode;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Override
    public ResponseData<List<RoleMenuDetailVO>> getRoleMenuList(Long roleId) {
        List<RoleMenuDetailVO> roleMenuList = baseMapper.getRoleMenuList(roleId);
        List<RoleMenuDetailVO> list = roleMenuList.stream()
                .filter(menu -> Objects.equals(0L, menu.getParentId()))
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
    public ResponseData<List<LoginMenuDetailVO>> listMenuAfterLogin() {
        return null;
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
}
