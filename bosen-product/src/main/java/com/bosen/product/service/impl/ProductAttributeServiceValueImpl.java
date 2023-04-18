package com.bosen.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.product.domain.ProductAttributeValueDO;
import com.bosen.product.mapper.ProductAttributeValueMapper;
import com.bosen.product.service.IProductAttributeValueService;
import org.springframework.stereotype.Service;

@Service
public class ProductAttributeServiceValueImpl extends ServiceImpl<ProductAttributeValueMapper, ProductAttributeValueDO> implements IProductAttributeValueService {

}
