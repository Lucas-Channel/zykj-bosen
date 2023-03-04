package com.bosen.product.controller;

import cn.hutool.db.Page;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.product.domain.ProductSkuDO;
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

    @GetMapping("/pageList")
    public ResponseData<PageData<ProductSkuDetailVO>> pageList(ProductSkuQueryVO queryVO) {
        return skuService.pageList(queryVO);
    }

}
