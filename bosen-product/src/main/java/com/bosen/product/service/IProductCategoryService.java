package com.bosen.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.product.domain.ProductCategoryDO;
import com.bosen.product.vo.response.ProductCategoryDetailVO;

import java.util.List;

public interface IProductCategoryService extends IService<ProductCategoryDO> {

    ResponseData<List<ProductCategoryDetailVO>> listCategoryTree();
}
