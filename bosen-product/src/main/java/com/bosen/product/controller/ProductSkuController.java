package com.bosen.product.controller;

import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.product.domain.ProductSkuDO;
import com.bosen.product.domain.ProductSkuMemberPriceDO;
import com.bosen.product.domain.ProductSkuWholesalePriceDO;
import com.bosen.product.service.IProductSkuService;
import com.bosen.product.vo.request.ProductSkuQueryVO;
import com.bosen.product.vo.request.ProductSkuUpsertVO;
import com.bosen.product.vo.response.ProductSkuDetailVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/product/sku")
public class ProductSkuController {

    @Resource
    private IProductSkuService skuService;

    /**
     * 更新商品库存
     * @return 结果
     */
    @PostMapping("/updateStock")
    public ResponseData<Void> updateStock(@RequestBody ProductSkuDO productSkuDO) {
        return ResponseData.judge(skuService.updateById(productSkuDO));
    }

    /**
     * 创建商品库存
     * @return 结果
     */
    @PostMapping("/addSkuStock")
    public ResponseData<Void> addSkuStock(@RequestBody List<ProductSkuUpsertVO> skuUpsertVOList) {
        return skuService.addSkuStock(skuUpsertVOList);
    }

    /**
     * 分页获取sku信息
     * @param queryVO 参数
     * @return 结果
     */
    @GetMapping("/pageList")
    public ResponseData<PageData<ProductSkuDetailVO>> pageList(ProductSkuQueryVO queryVO) {
        return skuService.pageList(queryVO);
    }

    /**
     * 设置批发价格
     * @param prices 价格范围
     * @param skuId sku
     * @return 结果
     */
    @PostMapping("/setWholesalePrice")
    public ResponseData<Void> setWholesalePrice(@RequestBody List<ProductSkuWholesalePriceDO> prices, @RequestParam Long skuId) {
        return skuService.setWholesalePrice(prices, skuId);
    }

    /**
     * 设置会员价格
     * @param prices 价格范围
     * @param skuId sku
     * @return 结果
     */
    @PostMapping("/setMemberPrice")
    public ResponseData<Void> setMemberPrice(@RequestBody List<ProductSkuMemberPriceDO> prices, @RequestParam Long skuId) {
        return skuService.setMemberPrice(prices, skuId);
    }

}
