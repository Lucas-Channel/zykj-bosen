package com.bosen.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.admin.domain.ShopDO;
import com.bosen.admin.mapper.ShopMapper;
import com.bosen.admin.service.IShopService;
import org.springframework.stereotype.Service;

/**
 * 平台商城
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/14
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, ShopDO> implements IShopService {
}
