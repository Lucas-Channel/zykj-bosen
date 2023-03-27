package com.bosen.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.system.domain.TenantDO;
import com.bosen.system.mapper.TenantMapper;
import com.bosen.system.service.ITenantService;
import org.springframework.stereotype.Service;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/27
 */
@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper, TenantDO> implements ITenantService {
}
