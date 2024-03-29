package com.bosen.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.product.domain.ProductCategoryBrandDO;
import com.bosen.product.vo.request.ProductCategoryBrandQueryVO;
import com.bosen.product.vo.request.ProductCategoryBrandUpsertVO;
import com.bosen.product.vo.response.ProductCategoryBrandDetailVO;
import com.bosen.product.vo.response.ProductCategoryWithBrandVO;

import java.util.List;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/2
 */
public interface IProductCategoryBrandService extends IService<ProductCategoryBrandDO> {

    ResponseData<PageData<ProductCategoryBrandDetailVO>> pageList(ProductCategoryBrandQueryVO queryVO);

    ResponseData<List<ProductCategoryWithBrandVO>> listForMobile(ProductCategoryBrandQueryVO queryVO);

    ResponseData<Void> upsertCategoryBrand(ProductCategoryBrandUpsertVO upsertVO);

}
