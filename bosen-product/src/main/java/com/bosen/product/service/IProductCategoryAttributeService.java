package com.bosen.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.common.constant.response.PageData;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.product.domain.ProductCategoryAttributeDO;
import com.bosen.product.vo.request.ProductCategoryAttributeQueryVO;
import com.bosen.product.vo.request.ProductCategoryAttributeUpsert;
import com.bosen.product.vo.response.ProductCategoryAttributeDetailVO;

import java.util.List;

public interface IProductCategoryAttributeService extends IService<ProductCategoryAttributeDO> {

    ResponseData<PageData<ProductCategoryAttributeDetailVO>> pageList(ProductCategoryAttributeQueryVO queryVO);

    ResponseData<Void> upsert(List<ProductCategoryAttributeUpsert> upsert);
}
