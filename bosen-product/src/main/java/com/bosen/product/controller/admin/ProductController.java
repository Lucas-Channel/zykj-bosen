package com.bosen.product.controller.admin;

import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.vo.request.ApproveInfoVO;
import com.bosen.product.domain.ProductDO;
import com.bosen.product.service.IProductService;
import com.bosen.product.vo.request.ProductQueryVO;
import com.bosen.product.vo.response.ProductDetailVO;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 商品spu管理-平台后台
 */
@RestController
@RequestMapping("/product/admin")
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
     * 商品审核
     * @param approveInfoVO
     * @return
     */
    @PostMapping("/approve")
    public ResponseData<Void> approveProduct(@RequestBody @Valid ApproveInfoVO approveInfoVO) {
        return productService.approveProduct(approveInfoVO);
    }

}
