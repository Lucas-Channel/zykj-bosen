package com.bosen.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.product.domain.ProductSkuDO;
import com.bosen.product.mapper.ProductSkuMapper;
import com.bosen.product.service.IProductSkuService;
import org.springframework.stereotype.Service;

@Service
public class ProductSkuServiceImpl extends ServiceImpl<ProductSkuMapper, ProductSkuDO> implements IProductSkuService {

}
