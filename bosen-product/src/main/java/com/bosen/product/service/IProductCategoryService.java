package com.bosen.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.product.domain.ProductCategory;
import com.bosen.product.vo.response.ProductCategoryDetailVO;

import java.util.List;

public interface IProductCategoryService extends IService<ProductCategory> {

    ResponseData<List<ProductCategoryDetailVO>> listCategoryTree();
}
