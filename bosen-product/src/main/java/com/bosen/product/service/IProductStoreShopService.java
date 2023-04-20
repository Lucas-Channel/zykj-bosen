package com.bosen.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.product.domain.ProductStoreShopDO;
import com.bosen.product.vo.response.ProductStoreShopDetailVO;

import java.util.List;

/**
 * 商品上架关联商城和店铺中间表(ProductStoreShop)表服务接口
 *
 * @author Lucas
 * @since 2023-04-20 21:09:47
 */
public interface IProductStoreShopService extends IService<ProductStoreShopDO> {
    ResponseData<Boolean> deleteByProductIdPhysic(List<String> productIds);

    ResponseData<List<ProductStoreShopDetailVO>> listByProductId(String productId);
}

