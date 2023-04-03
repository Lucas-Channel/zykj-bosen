package com.bosen.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.product.domain.ProductLabelDO;
import com.bosen.product.mapper.ProductLabelMapper;
import com.bosen.product.service.IProductLabelService;
import org.springframework.stereotype.Service;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/3
 */
@Service
public class ProductLabelServiceImpl extends ServiceImpl<ProductLabelMapper, ProductLabelDO> implements IProductLabelService {
}
