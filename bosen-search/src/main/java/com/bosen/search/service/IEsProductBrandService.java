package com.bosen.search.service;

import com.bosen.common.constant.response.ResponseData;
import com.bosen.elasticsearch.domain.EsProductBrandDO;

import java.util.List;

/**
 * es 品牌接口
 * @author Lucas
 * @version 2.0.0
 * @date 2023/5/25
 */
public interface IEsProductBrandService {
    ResponseData<Void> rackingProductBrand(List<EsProductBrandDO> brandDOList);

    ResponseData<Void> downProductBrand(List<String> ids);
}
