package com.bosen.product.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品上架关联商城和店铺中间表
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/15
 */
@Data
@TableName("bs_product_store_shop")
public class ProductStoreShopDO extends BaseEntityData implements Serializable {
    private static final long serialVersionUID = -3798555271201860976L;

    /**
     * 商城id
     */
    private String shopId;

    /**
     * 店铺id
     */
    private String storeId;

    /**
     * 商品id
     */
    private String productId;
}
