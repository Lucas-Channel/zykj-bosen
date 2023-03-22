package com.bosen.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosen.admin.domain.MerchantRoleRelationDO;
import com.bosen.admin.domain.SystemRole;

import java.util.List;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/22
 */
public interface MerchantRoleRelationMapper extends BaseMapper<MerchantRoleRelationDO> {
    /**
     * 通过用户id查询角色列表
     * @param merchantId
     * @return
     */
    List<SystemRole> getMerchantRoleList(Long merchantId);
}
