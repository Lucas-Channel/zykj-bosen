package com.bosen.search.service;

import com.bosen.common.constant.response.ResponseData;
import com.bosen.elasticsearch.domain.ESProductSkuModelDO;

import java.util.List;

/**
 * es 商品搜索接口
 * @author Lucas
 * @version 2.0.0
 * @date 2023/5/9
 */
public interface IEsProductService {
    ResponseData<Void> rackingProduct(List<ESProductSkuModelDO> productSkuList);
}
