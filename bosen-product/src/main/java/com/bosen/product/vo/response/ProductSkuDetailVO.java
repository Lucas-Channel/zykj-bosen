package com.bosen.product.vo.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品sku明细
 */
@Data
public class ProductSkuDetailVO {
    private String id;
    /**
     * sku编码
     */
    private String skuCode;

    /**
     * SKU 名称
     */
    private String name;

    /**
     * 商品 ID
     */
    private String productId;

    /**
     * 规格ID，多个使用英文逗号(,)分割
     */
    private String specIds;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * vip价格
     */
    private BigDecimal vipPrice;

    /**
     * 库存数量
     */
    private Integer stockNum;

    /**
     * 锁定库存数量
     */
    private Integer lockedStockNum;

    /**
     * 商品图片地址
     */
    private String picUrl;
}
