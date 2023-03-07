package com.bosen.product.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品sku会员临时价格
 */
@Data
public class ProductSkuMemberPriceDO implements Serializable {
    private static final long serialVersionUID = -2010485813658137154L;
    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 价格
     */
    private BigDecimal price;
}
