package com.bosen.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.system.domain.UserQuickMenuDO;
import com.bosen.system.mapper.UserQuickMenuMapper;
import com.bosen.system.vo.request.QuickMenuUpsertVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.bosen.system.service.IUserQuickMenuService;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 用户快捷操作菜单表(BsUserQuickMenu)表服务实现类
 *
 * @author Lucas
 * @since 2023-07-13 14:16:43
 */
@Service
public class UserQuickMenuServiceImpl extends ServiceImpl<UserQuickMenuMapper, UserQuickMenuDO> implements IUserQuickMenuService {

    @Override
    public ResponseData<List<UserQuickMenuDO>> listByLoginUser() {
        String userId = "", roleId = "";
        return ResponseData.success(this.lambdaQuery().eq(UserQuickMenuDO::getUserId, userId).eq(UserQuickMenuDO::getRoleId, roleId).list());
    }

    @Override
    public ResponseData<Void> upsert(QuickMenuUpsertVO quickMenuUpsertVO) {
        UserQuickMenuDO userQuickMenuDO = StringUtils.hasLength(quickMenuUpsertVO.getId()) ? baseMapper.selectById(quickMenuUpsertVO.getId()) : new UserQuickMenuDO();
        BeanUtils.copyProperties(quickMenuUpsertVO, userQuickMenuDO);
        userQuickMenuDO.setUserId("");
        userQuickMenuDO.setRoleId("");
        this.saveOrUpdate(userQuickMenuDO);
        return ResponseData.success();
    }
}

