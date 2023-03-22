package com.bosen.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.admin.domain.MerchantRoleRelationDO;
import com.bosen.admin.domain.SystemRole;

import java.util.List;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/22
 */
public interface IMerchantRoleRelationService extends IService<MerchantRoleRelationDO> {
    /**
     * 通过用户id查询角色列表
     * @param merchantId
     * @return 集合
     */
    List<SystemRole> getMerchantRoleList(Long merchantId);
}
