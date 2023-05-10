package com.bosen.search.controller;

import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.elasticsearch.domain.ESProductSkuModelDO;
import com.bosen.elasticsearch.domain.EsProductSalesAreaDO;
import com.bosen.search.service.IEsProductService;
import com.bosen.search.vo.request.ProductQueryVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * ES 商品搜索服务
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/21
 */
@RestController
@RequestMapping("/es/product")
public class EsProductController {

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
}
