package com.bosen.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.product.domain.ProductAttributeDO;
import com.bosen.product.mapper.ProductAttributeMapper;
import com.bosen.product.service.IProductAttributeService;
import org.springframework.stereotype.Service;

@Service
public class ProductAttributeServiceImpl extends ServiceImpl<ProductAttributeMapper, ProductAttributeDO> implements IProductAttributeService {

}
