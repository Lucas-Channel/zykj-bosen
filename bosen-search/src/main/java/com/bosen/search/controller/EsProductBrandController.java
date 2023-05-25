package com.bosen.search.controller;

import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.elasticsearch.domain.ESProductAttributeAndValueModelDO;
import com.bosen.elasticsearch.domain.ESProductSkuModelDO;
import com.bosen.elasticsearch.domain.EsProductSalesAreaDO;
import com.bosen.search.service.IEsProductService;
import com.bosen.search.vo.request.ProductQueryVO;
import com.bosen.search.vo.request.UpdateSkuSalesCountVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * ES 商品搜索服务
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/21
 */
@RestController
@RequestMapping("/es/brand")
public class EsProductBrandController {

    @Resource
    private IEsProductService esProductService;

    /**
     * 获取商品spu的销售区域
     * @param spuId spuId
     * @return 列表
     */
    @GetMapping("/getProductSalesArea/{spuId}")
    public ResponseData<List<EsProductSalesAreaDO>> getProductSalesArea(@PathVariable("spuId") String spuId) {
        return esProductService.getProductSalesArea(spuId);
    }

    /**
     * 分页查询es中的sku商品数据
     * @param queryVO 查询参数
     * @return 结果
     */
    @PostMapping("/pageData")
    public ResponseData<PageData<ESProductSkuModelDO>> pageData(@RequestBody ProductQueryVO queryVO) {
        return esProductService.pageData(queryVO);
    }

    /**
     * 更新sku已售数量
     * @param updateSkuSalesCountVOS 参数
     * @return 结果
     */
    @PostMapping("/updateEsSalesCount")
    public ResponseData<Void> updateEsSalesCount(@RequestBody List<UpdateSkuSalesCountVO> updateSkuSalesCountVOS) {
        // todo 需要接入登录
        return esProductService.updateEsSalesCount(updateSkuSalesCountVOS);
    }

    /**
     * 获取spu下已上架的sku属性规格信息
     * @param spuId 商品id
     * @return 集合
     */
    @GetMapping("/getSpuHasRackingAttribute/{spuId}")
    public ResponseData<Map<String, Map<String, List<ESProductAttributeAndValueModelDO>>>> getSpuHasRackingAttribute(@PathVariable("spuId") String spuId, String shopId, String storeId) {
        return esProductService.getSpuHasRackingAttribute(spuId, shopId, storeId);
    }
}
