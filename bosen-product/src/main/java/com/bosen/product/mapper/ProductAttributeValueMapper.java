package com.bosen.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosen.elasticsearch.domain.ESProductAttributeAndValueModelDO;
import com.bosen.product.domain.ProductAttributeValueDO;

import java.util.List;

public interface ProductAttributeValueMapper extends BaseMapper<ProductAttributeValueDO> {
    List<ESProductAttributeAndValueModelDO> listEsProductAttributeAndValueBySkuId(String skuId);
}
