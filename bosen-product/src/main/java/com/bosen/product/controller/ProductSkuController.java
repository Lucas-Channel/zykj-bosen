package com.bosen.product.controller;

import com.bosen.common.constant.response.ResponseData;
import com.bosen.product.domain.ProductSkuDO;
import com.bosen.product.service.IProductSkuService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

}
