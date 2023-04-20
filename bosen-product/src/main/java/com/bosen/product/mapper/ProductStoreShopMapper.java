package com.bosen.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosen.product.domain.ProductStoreShopDO;
import com.bosen.product.vo.response.ProductStoreShopDetailVO;

import java.util.List;

/**
 * 商品上架关联商城和店铺中间表(ProductStoreShop)表数据库访问层
 *
 * @author Lucas
 * @since 2023-04-20 21:09:45
 */
public interface ProductStoreShopMapper extends BaseMapper<ProductStoreShopDO> {
    int deleteByProductIdPhysic(List<String> productIds);

    List<ProductStoreShopDetailVO> listByProductId(String productId);
}

