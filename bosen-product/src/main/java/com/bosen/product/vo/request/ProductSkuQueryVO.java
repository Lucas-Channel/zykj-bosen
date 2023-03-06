package com.bosen.product.vo.request;

import com.bosen.common.domain.PageVO;
import lombok.Data;

/**
 * 商品sku查询参数
 */
@Data
public class ProductSkuQueryVO extends PageVO {
    /**
     * sku编码
     */
    private String skuCode;

    /**
     * SKU 名称
     */
    private String skuName;

    /**
     * 商品 名称
     */
    private Long productName;

}
