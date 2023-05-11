package com.bosen.product.feign;

import com.bosen.common.constant.response.ResponseData;
import com.bosen.elasticsearch.domain.ESProductSkuModelDO;
import com.bosen.elasticsearch.vo.request.DownProductRequestVO;
import com.bosen.product.feign.fallback.EsApiFeignServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 调用es feign接口
 * @author Lucas
 */
@FeignClient(value = "bosen-search", fallback = EsApiFeignServiceFallback.class)
public interface EsApiFeignService {

    /**
     * sku 商品上架
     * @param productSkuList sku集合
     * @return 结果
     */
    @PostMapping("/es/api/product/racking")
    ResponseData<Void> rackingProduct(@RequestBody List<ESProductSkuModelDO> productSkuList);

    /**
     * spu 商品下架
     * @param downProductRequestVO spu集合
     * @return 结果
     */
    @PostMapping("/es/api/product/downProduct")
    ResponseData<Void> downProduct(@RequestBody DownProductRequestVO downProductRequestVO);
}
