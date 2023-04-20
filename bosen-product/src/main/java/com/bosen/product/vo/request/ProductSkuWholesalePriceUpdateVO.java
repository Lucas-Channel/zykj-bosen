package com.bosen.product.vo.request;

import com.bosen.product.domain.ProductSkuWholesalePriceDO;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * 商品sku 批发价格
 */
@Data
public class ProductSkuWholesalePriceUpdateVO implements Serializable {
    private static final long serialVersionUID = -5174796571297105778L;

    @NotBlank(message = "skuId不能为空")
    private String skuId;

    @NotEmpty(message = "价格不能为空")
    private List<ProductSkuWholesalePriceDO> prices;
}
