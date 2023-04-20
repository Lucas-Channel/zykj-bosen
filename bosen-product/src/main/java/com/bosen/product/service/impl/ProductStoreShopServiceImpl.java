package com.bosen.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.product.domain.ProductStoreShopDO;
import com.bosen.product.mapper.ProductStoreShopMapper;
import com.bosen.product.service.IProductStoreShopService;
import com.bosen.product.vo.response.ProductStoreShopDetailVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品上架关联商城和店铺中间表(ProductStoreShop)表服务实现类
 *
 * @author Lucas
 * @since 2023-04-20 21:09:47
 */
@Service
public class ProductStoreShopServiceImpl extends ServiceImpl<ProductStoreShopMapper, ProductStoreShopDO> implements IProductStoreShopService {

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseData<Boolean> deleteByProductIdPhysic(List<String> productIds) {
        return ResponseData.judge(baseMapper.deleteByProductIdPhysic(productIds) >= 0);
    }

    @Override
    public ResponseData<List<ProductStoreShopDetailVO>> listByProductId(String productId) {
        return ResponseData.success(baseMapper.listByProductId(productId));
    }
}

