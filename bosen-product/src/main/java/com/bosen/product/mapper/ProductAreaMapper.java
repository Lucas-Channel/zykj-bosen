package com.bosen.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosen.elasticsearch.domain.EsProductSalesAreaDO;
import com.bosen.product.domain.ProductAreaDO;

import java.util.List;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/12
 */
public interface ProductAreaMapper extends BaseMapper<ProductAreaDO> {
    List<EsProductSalesAreaDO> listSalesAreaByProductId(String productId);
}
