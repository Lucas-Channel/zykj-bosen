package com.bosen.product.controller;

import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.product.domain.ProductDO;
import com.bosen.product.service.IProductService;
import com.bosen.product.vo.request.ProductQueryVO;
import com.bosen.product.vo.request.ProductUpsertVO;
import com.bosen.product.vo.response.ProductDetailVO;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 商品spu管理
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Resource
    private IProductService productService;

    /**
     * 商品分页列表
     * @return 结果
     */
    @GetMapping("/pages")
   public ResponseData<PageData<ProductDetailVO>> listPages(ProductQueryVO queryVO) {
        return productService.listPages(queryVO);
    }

    /**
     * 商品详情
     * @param id id
     * @return 结果
     */
    @GetMapping("/detail/{id}")
    public ResponseData<ProductDetailVO> detail(@PathVariable Long id) {
        ProductDO pmsSpuDetailVO = productService.getById(id);
        ProductDetailVO detailVO = new ProductDetailVO();
        BeanUtils.copyProperties(pmsSpuDetailVO, detailVO);
        // 后续需要查询商品的规格属性和sku
        return ResponseData.success(detailVO);
    }

    /**
     * 新增/修改商品
     * @return
     */
    @PostMapping("/upsertProduct")
    public ResponseData<Void> upsertProduct(@RequestBody @Valid ProductUpsertVO formData) {
        return productService.upsertProduct(formData);
    }


    /**
     * 删除商品
     * @param ids ids
     * @return 结果
     */
    @PostMapping("/deleteByIds")
    public ResponseData<Void> deleteByIds(@RequestBody List<Long> ids) {
        return ResponseData.judge(productService.removeBatchByIds(ids));
    }
}
