package com.bosen.product.controller.admin;

import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.vo.request.ApproveBatchInfoVO;
import com.bosen.common.vo.request.ApproveInfoVO;
import com.bosen.product.service.IProductService;
import com.bosen.product.vo.request.ProductQueryVO;
import com.bosen.product.vo.response.ProductDetailVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 商品spu管理-平台后台
 */
@RestController
@RequestMapping("/product/admin")
public class AdminProductController {

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
     * 商品审核
     * @param approveInfoVO
     * @return
     */
    @PostMapping("/approve")
    public ResponseData<Void> approveProduct(@RequestBody @Valid ApproveInfoVO approveInfoVO) {
        return productService.approveProduct(approveInfoVO);
    }


    /**
     * 商品审核-批量
     * @param approveBatchInfoVO
     * @return
     */
    @PostMapping("/approveBatch")
    public ResponseData<Void> approveBatch(@RequestBody @Valid ApproveBatchInfoVO approveBatchInfoVO) {
        return productService.approveBatch(approveBatchInfoVO);
    }
}
