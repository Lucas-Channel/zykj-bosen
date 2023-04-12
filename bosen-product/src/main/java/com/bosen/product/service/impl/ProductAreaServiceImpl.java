package com.bosen.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.product.domain.ProductAreaDO;
import com.bosen.product.mapper.ProductAreaMapper;
import com.bosen.product.service.IProductAreaService;
import org.springframework.stereotype.Service;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/12
 */
@Service
public class ProductAreaServiceImpl extends ServiceImpl<ProductAreaMapper, ProductAreaDO> implements IProductAreaService {
}
