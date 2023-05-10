package com.bosen.product.vo.request;

import com.bosen.common.domain.PageVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 商品sku查询参数
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
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
    private String productName;

    private String merchantId;

    /**
     * 商品所属商家角色id
     */
    private String merchantRoleId;

}
