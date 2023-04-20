package com.bosen.product.controller.merchant;

import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.product.service.IProductService;
import com.bosen.product.vo.request.ProductQueryVO;
import com.bosen.product.vo.request.ProductRackingOrDownVO;
import com.bosen.product.vo.request.ProductUpsertVO;
import com.bosen.product.vo.response.ProductDetailVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 商品spu管理-商家后台
 */
@RestController
@RequestMapping("/product/merchant")
public class MerchantProductController {
    @Resource
    private IProductService productService;

    /**
     * 商品分页列表
     * @return 结果
     */
    @GetMapping("/pages")
   public ResponseData<PageData<ProductDetailVO>> listPagesForMerchant(ProductQueryVO queryVO) {
        return productService.listPagesForMerchant(queryVO);
    }

    /**
     * 商品详情
     * @param id id
     * @return 结果
     */
    @GetMapping("/detail/{id}")
    public ResponseData<ProductDetailVO> detail(@PathVariable("id") String id) {
        return productService.detail(id);
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
     * 商品-提交审核
     * @param id 商品id
     * @return
     */
    @PostMapping("/submitApproveProduct")
    public ResponseData<Void> submitApproveProduct(@RequestBody @Valid @NotBlank(message = "商品id不能为空") String id) {
        return productService.submitApproveProduct(id);
    }


    /**
     * 商品-提交审核-批量
     * @param ids 商品id
     * @return
     */
    @PostMapping("/submitApproveProductBatch")
    public ResponseData<Void> submitApproveProductBatch(@RequestBody @Valid @NotBlank(message = "商品id不能为空") List<String> ids) {
        return productService.submitApproveProductBatch(ids);
    }


    /**
     * 删除商品
     * @param ids ids
     * @return 结果
     */
    @PostMapping("/deleteByIds")
    public ResponseData<Void> deleteByIds(@RequestBody @Valid @NotEmpty(message = "商品id不能为空") List<Long> ids) {
        return productService.deleteByIds(ids);
    }

    /**
     * 上架
     * @return
     */
    @PostMapping("/rackingProduct")
    public ResponseData<Void> rackingProduct(@RequestBody @Valid ProductRackingOrDownVO productRackingOrDownVO) {
        return productService.rackingProduct(productRackingOrDownVO);
    }
}
