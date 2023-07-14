package com.bosen.product.controller.merchant;

import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.product.service.IProductSkuService;
import com.bosen.product.vo.request.ProductSkuMemberPriceUpdateVO;
import com.bosen.product.vo.request.ProductSkuQueryVO;
import com.bosen.product.vo.request.ProductSkuUpsertVO;
import com.bosen.product.vo.request.ProductSkuWholesalePriceUpdateVO;
import com.bosen.product.vo.response.ProductSkuDetailVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 商家后台sku管理
 */
@RestController
@RequestMapping("/product/sku")
public class ProductSkuController {

    @Resource
    private IProductSkuService skuService;

    /**
     * 更新商品库存
     * @return 结果
     */
    @PostMapping("/updateSKu")
    public ResponseData<Void> updateSKu(@RequestBody ProductSkuUpsertVO productSkuUpsertVO) {
        return skuService.updateSKu(productSkuUpsertVO);
    }

    /**
     * 创建商品库存
     * @return 结果
     */
    @PostMapping("/addSku")
    public ResponseData<Void> addSku(@RequestBody List<ProductSkuUpsertVO> skuUpsertVOList) {
        return skuService.addSku(skuUpsertVOList);
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
     * @param updateVO 参数
     * @return 结果
     */
    @PostMapping("/setWholesalePrice")
    public ResponseData<Void> setWholesalePrice(@RequestBody @Valid ProductSkuWholesalePriceUpdateVO updateVO) {
        return skuService.setWholesalePrice(updateVO.getPrices(), updateVO.getSkuId());
    }

    /**
     * 设置会员价格
     * @param updateVO 参数
     * @return 结果
     */
    @PostMapping("/setMemberPrice")
    public ResponseData<Void> setMemberPrice(@RequestBody ProductSkuMemberPriceUpdateVO updateVO) {
        return skuService.setMemberPrice(updateVO.getPrices(), updateVO.getSkuId());
    }

}
