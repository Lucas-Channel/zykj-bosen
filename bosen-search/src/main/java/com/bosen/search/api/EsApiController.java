package com.bosen.search.api;

import com.bosen.common.constant.response.ResponseData;
import com.bosen.elasticsearch.domain.ESProductSkuModelDO;
import com.bosen.elasticsearch.vo.request.DownProductRequestVO;
import com.bosen.search.service.IEsProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * es 内部调用api接口 控制层
 * @author Lucas
 * @version 2.0.0
 * @date 2023/5/9
 */
@RestController
@RequestMapping("/es/api/product")
public class EsApiController {

    @Resource
    private IEsProductService esProductService;

    /**
     * sku 商品上架
     * @param productSkuList sku集合
     * @return 结果
     */
    @PostMapping("/racking")
    public ResponseData<Void> rackingProduct(@RequestBody List<ESProductSkuModelDO> productSkuList) {
        return esProductService.rackingProduct(productSkuList);
    }

    /**
     * 依据spu下架商品
     * @param downProductRequestVO 参数
     * @return 结果
     */
    @PostMapping("/downProduct")
    public ResponseData<Void> downProduct(@RequestBody DownProductRequestVO downProductRequestVO) {
        assert Objects.nonNull(downProductRequestVO.getSpuIds());
        assert Objects.nonNull(downProductRequestVO.getShopIds());
        assert Objects.nonNull(downProductRequestVO.getStoreIds());
        return esProductService.downProduct(downProductRequestVO);
    }
}
