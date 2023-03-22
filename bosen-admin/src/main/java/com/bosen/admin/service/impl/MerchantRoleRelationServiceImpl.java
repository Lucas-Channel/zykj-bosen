package com.bosen.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.admin.domain.MerchantRoleRelationDO;
import com.bosen.admin.domain.SystemRole;
import com.bosen.admin.mapper.MerchantRoleRelationMapper;
import com.bosen.admin.service.IMerchantRoleRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/22
 */
@Service
@Slf4j
public class MerchantRoleRelationServiceImpl extends ServiceImpl<MerchantRoleRelationMapper, MerchantRoleRelationDO> implements IMerchantRoleRelationService {
    @Override
    public List<SystemRole> getMerchantRoleList(Long merchantId) {
        return this.baseMapper.getMerchantRoleList(merchantId);
    }
}
