package com.bosen.product.controller.admin;

import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.product.service.IProductCategoryAttributeService;
import com.bosen.product.vo.request.ProductCategoryAttributeQueryVO;
import com.bosen.product.vo.request.ProductCategoryAttributeUpsert;
import com.bosen.product.vo.response.ProductCategoryAttributeDetailVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 平台品类默认属性/规格
 */
@RestController
@RequestMapping("/product/category/attribute")
public class ProductCategoryAttributeController {
    @Resource
    private IProductCategoryAttributeService productCategoryAttributeService;

    /**
     * 分页获取品类默认属性/规格
     * @param queryVO 参数
     * @return 结果
     */
    @GetMapping("/pageList")
    public ResponseData<PageData<ProductCategoryAttributeDetailVO>> pageList(ProductCategoryAttributeQueryVO queryVO) {
        return productCategoryAttributeService.pageList(queryVO);
    }

    /**
     * 新增/修改
     * @param upsert 参数
     * @return 结果
     */
    @PostMapping("/upsert")
    public ResponseData<Void> upsert(@RequestBody List<ProductCategoryAttributeUpsert> upsert) {
        return productCategoryAttributeService.upsert(upsert);
    }

    /**
     * 删除
     * @param ids ids
     * @return 结果
     */
    @PostMapping("/deleteByIds")
    public ResponseData<Void> deleteByIds(@RequestBody List<Long> ids) {
        return ResponseData.judge(productCategoryAttributeService.removeBatchByIds(ids));
    }
}
