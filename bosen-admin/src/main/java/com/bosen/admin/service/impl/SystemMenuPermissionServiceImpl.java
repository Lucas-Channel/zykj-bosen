package com.bosen.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.admin.domain.SystemMenuPermissionDO;
import com.bosen.admin.mapper.SystemMenuPermissionRelationMapper;
import com.bosen.admin.service.ISystemMenuPermissionService;
import com.bosen.admin.vo.response.SystemMenuPermissionDetail;
import com.bosen.admin.vo.resquest.MenuPermissionQueryVO;
import com.bosen.admin.vo.resquest.MenuPermissionUpsertVO;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/25
 */
@Slf4j
@Service
public class SystemMenuPermissionServiceImpl extends ServiceImpl<SystemMenuPermissionRelationMapper, SystemMenuPermissionDO> implements ISystemMenuPermissionService {
    @Override
    public ResponseData<PageData<SystemMenuPermissionDetail>> listPermissionsWithPage(MenuPermissionQueryVO query) {
        Page<SystemMenuPermissionDO> page = this.page(new Page<>(query.getCurrent(), query.getSize()), new LambdaQueryWrapper<SystemMenuPermissionDO>()
                .like(StringUtils.hasLength(query.getName()), SystemMenuPermissionDO::getName, query.getName())
                .eq(Objects.nonNull(query.getMenuId()), SystemMenuPermissionDO::getMenuId, query.getMenuId())
                .orderByDesc(SystemMenuPermissionDO::getCreateTime));
        return ResponseData.success(new PageData<>(page.getTotal(), page.getRecords().stream().map(i -> {
            SystemMenuPermissionDetail menuPermissionDetail = new SystemMenuPermissionDetail();
            BeanUtils.copyProperties(i, menuPermissionDetail);
            return menuPermissionDetail;
        }).collect(Collectors.toList())));
    }

    @Override
    public ResponseData<Void> upsertPermission(MenuPermissionUpsertVO permission) {
        SystemMenuPermissionDO menuPermissionRelation = new SystemMenuPermissionDO();
        BeanUtils.copyProperties(permission, menuPermissionRelation);
        if (Objects.isNull(permission.getId())) {
            menuPermissionRelation.setCreateTime(LocalDateTime.now());
        }
        return ResponseData.judge(this.saveOrUpdate(menuPermissionRelation));
    }
}
