package com.bosen.product.controller;

import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.product.service.IProductCategoryBrandService;
import com.bosen.product.vo.request.ProductCategoryBrandQueryVO;
import com.bosen.product.vo.request.ProductCategoryBrandUpsertVO;
import com.bosen.product.vo.response.ProductCategoryBrandDetailVO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 品类品牌
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/3
 */
@RestController
@RequestMapping("/product/category/brand")
public class ProductCategoryBrandController {

    @Resource
    private IProductCategoryBrandService categoryBrandService;

    /**
     * 分页查询
     * @param queryVO 参数
     * @return 结果
     */
    @GetMapping("/pageList")
    public ResponseData<PageData<ProductCategoryBrandDetailVO>> pageList(ProductCategoryBrandQueryVO queryVO) {
        return categoryBrandService.pageList(queryVO);
    }

    /**
     * 查询所有品类品牌
     * @param queryVO 参数
     * @return 结果
     */
    @GetMapping("/listForMobile")
    @Cacheable(cacheNames = "product:category:brand", key = "'list'", unless = "!#result?.code == 200")
    public ResponseData<List<ProductCategoryBrandDetailVO>> listForMobile(ProductCategoryBrandQueryVO queryVO) {
        return categoryBrandService.listForMobile(queryVO);
    }

    /**
     * 新增/修改 品类品牌
     * @return 结果
     */
    @PostMapping("/upsertCategoryBrand")
    public ResponseData<Void> upsertCategoryBrand(@RequestBody @Valid ProductCategoryBrandUpsertVO upsertVO) {
        return categoryBrandService.upsertCategoryBrand(upsertVO);
    }

    /**
     * 删除
     * @param ids ids
     * @return 结果
     */
    @PostMapping("/deleteByIds")
    public ResponseData<Void> deleteByIds(@RequestBody List<Long> ids) {
        return ResponseData.judge(categoryBrandService.removeBatchByIds(ids));
    }


}
