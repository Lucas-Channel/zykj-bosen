package com.bosen.product.controller;

import com.bosen.common.constant.response.ResponseData;
import com.bosen.product.domain.ProductCategory;
import com.bosen.product.service.IProductCategoryService;
import com.bosen.product.vo.response.ProductCategoryDetailVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 品类管理
 */
@RestController
@RequestMapping("/product/category")
public class ProductCategoryController {

    @Resource
    private IProductCategoryService productCategoryService;

    /**
     * 获取品类-树型结构
     * @return 结果
     */
    @GetMapping("/listCategoryTree")
    public ResponseData<List<ProductCategoryDetailVO>> listCategoryTree() {
        return productCategoryService.listCategoryTree();
    }

    /**
     * 新增/修改品类
     * @return 结果
     */
    @PostMapping("/upsertCategory")
    public ResponseData<Void> upsertCategory(@RequestBody ProductCategory category) {
        return ResponseData.judge(productCategoryService.saveOrUpdate(category));
    }

    /**
     * 删除
     * @param ids ids
     * @return 结果
     */
    @PostMapping("/deleteByIds")
    public ResponseData<Void> deleteByIds(@RequestBody List<Long> ids) {
        return ResponseData.judge(productCategoryService.removeBatchByIds(ids));
    }
}
