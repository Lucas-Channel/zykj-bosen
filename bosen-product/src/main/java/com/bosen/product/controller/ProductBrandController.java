package com.bosen.product.controller;

import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.product.domain.ProductBrandDO;
import com.bosen.product.service.IProductBrandService;
import com.bosen.product.vo.request.ProductBrandQueryVO;
import com.bosen.product.vo.response.ProductBrandDetailVO;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 管理端：品牌管理
 */
@RestController
@RequestMapping("/product/brand")
public class ProductBrandController {
    @Resource
    private IProductBrandService productBrandService;

    /**
     * 分页查询品牌列表
     * @param brandQueryVO 参数
     * @return 结果
     */
    @GetMapping("/page")
    public ResponseData<PageData<ProductBrandDetailVO>> page(ProductBrandQueryVO brandQueryVO) {
        return productBrandService.pageList(brandQueryVO);
    }

    /**
     * 新增/修改
     * @return
     */
    @PostMapping("/upsertBrand")
    @CacheEvict(cacheNames = "product:brand", key = "'list'", allEntries = true)
    public ResponseData<Void> upsertBrand(@RequestBody ProductBrandDO productBrandDO) {
        return ResponseData.judge(productBrandService.saveOrUpdate(productBrandDO));
    }

    @PostMapping("/deleteByIds")
    @CacheEvict(cacheNames = "product:brand", key = "'list'", allEntries = true)
    public ResponseData<Void> deleteByIds(@RequestBody List<String> ids) {
        return ResponseData.judge(productBrandService.removeBatchByIds(ids));
    }

    /**
     * 移动端获取品牌列表-查询筛选条件,
     * @return 结果
     */
    @GetMapping("/mobile/listBrand")
    @Cacheable(cacheNames = "product:brand", key = "'list'", unless = "#result?.data == null")
    public ResponseData<List<ProductBrandDetailVO>> listBrand() {
        List<ProductBrandDO> list = productBrandService.lambdaQuery().list();
        return ResponseData.success(list.stream().map(i -> {
            ProductBrandDetailVO de = new ProductBrandDetailVO();
            BeanUtils.copyProperties(i, de);
            return de;
        }).collect(Collectors.toList()));
    }
}
