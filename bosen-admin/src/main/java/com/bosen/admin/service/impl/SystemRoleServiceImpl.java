package com.bosen.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.admin.domain.SystemRole;
import com.bosen.admin.mapper.SystemRoleMapper;
import com.bosen.admin.service.ISystemRoleService;
import com.bosen.admin.vo.response.RoleDetailVO;
import com.bosen.admin.vo.resquest.RoleQueryVO;
import com.bosen.admin.vo.resquest.RoleUpsertVO;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.exception.BusinessException;
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
public class SystemRoleServiceImpl extends ServiceImpl<SystemRoleMapper, SystemRole> implements ISystemRoleService {
    @Override
    public ResponseData<PageData<RoleDetailVO>> getRolePageList(RoleQueryVO queryVO) {
        Page<SystemRole> page = this.page(new Page<>(queryVO.getCurrent(), queryVO.getSize()), new LambdaQueryWrapper<SystemRole>()
                    .like(StringUtils.hasLength(queryVO.getName()), SystemRole::getName, queryVO.getName())
                .orderByDesc(SystemRole::getCreateTime));
        return ResponseData.success(new PageData<>(page.getTotal(), page.getRecords().stream().map(i -> {
            RoleDetailVO roleDetailVO = new RoleDetailVO();
            BeanUtils.copyProperties(i, roleDetailVO);
            return roleDetailVO;
        }).collect(Collectors.toList())));
    }

    @Override
    public ResponseData<Void> upsertRole(RoleUpsertVO role) {
        Long count = this.count(new LambdaQueryWrapper<SystemRole>()
                .eq(SystemRole::getName, role.getName())
        );
        if (0 < count) {
            throw new BusinessException("角色名称已存在");
        }
        if (Objects.isNull(role.getId())) {
            role.setCreateTime(LocalDateTime.now());
        }
        SystemRole systemRole = new SystemRole();
        BeanUtils.copyProperties(role, systemRole);
        return ResponseData.judge(this.saveOrUpdate(systemRole));
    }


}
