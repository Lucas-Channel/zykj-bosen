package com.bosen.search.service;

import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.elasticsearch.domain.ESProductAttributeAndValueModelDO;
import com.bosen.elasticsearch.domain.ESProductSkuModelDO;
import com.bosen.elasticsearch.domain.EsProductSalesAreaDO;
import com.bosen.search.vo.request.ProductQueryVO;
import com.bosen.search.vo.request.UpdateSkuSalesCountVO;

import java.util.List;
import java.util.Map;

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

    /**
     * 更新es中sku的已销数量
     * @param updateSkuSalesCountVOS 参数
     * @return 结果
    **/
    ResponseData<Void> updateEsSalesCount(List<UpdateSkuSalesCountVO> updateSkuSalesCountVOS);

    /**
     * 获取spu下已上架的sku属性规格信息
     * @param spuId 商品id
     * @return 集合
     */
    ResponseData<Map<String, Map<String, List<ESProductAttributeAndValueModelDO>>>> getSpuHasRackingAttribute(String spuId);
}
