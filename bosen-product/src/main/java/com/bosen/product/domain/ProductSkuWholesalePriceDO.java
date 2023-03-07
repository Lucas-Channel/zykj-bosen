package com.bosen.product.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品sku 批发价格
 */
@Data
public class ProductSkuWholesalePriceDO implements Serializable {
    private static final long serialVersionUID = -1462562728219637052L;
    /**
     * 最小购买量
     */
    private Integer minQty;

    /**
     * 最大购买量
     */
    private Integer maxQty;

    /**
     * 价格
     */
    private BigDecimal price;
}
