package com.bosen.search.service;

import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.elasticsearch.domain.ESProductSkuModelDO;
import com.bosen.elasticsearch.domain.EsProductSalesAreaDO;
import com.bosen.search.vo.request.ProductQueryVO;

import java.util.List;

/**
 * es 商品搜索接口
 * @author Lucas
 * @version 2.0.0
 * @date 2023/5/9
 */
public interface IEsProductService {
    /**
     * 商品上架
     * @param productSkuList sku列表
     * @return 结果
     */
    ResponseData<Void> rackingProduct(List<ESProductSkuModelDO> productSkuList);

    /**
     * 获取商品spu的销售区域
     * @param spuId spuId
     * @return 列表
     */
    ResponseData<List<EsProductSalesAreaDO>> getProductSalesArea(String spuId);

    /**
     * 分页查询es中的sku商品数据
     * @param queryVO 查询参数
     * @return 结果
     */
    ResponseData<PageData<ESProductSkuModelDO>> pageData(ProductQueryVO queryVO);
}