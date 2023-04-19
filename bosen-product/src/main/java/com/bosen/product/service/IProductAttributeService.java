package com.bosen.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.product.domain.ProductAttributeDO;
import com.bosen.product.vo.request.ProductAttributeUpsertVO;
import com.bosen.product.vo.response.ProductAttributeDetailVO;

import java.util.List;

public interface IProductAttributeService extends IService<ProductAttributeDO> {
    ResponseData<Void> upsertProductAttribute(List<ProductAttributeUpsertVO> attrList, List<ProductAttributeUpsertVO> specList, String productId);

    List<ProductAttributeDetailVO> listProductAttributeDetailByProductId(String productId);
}
