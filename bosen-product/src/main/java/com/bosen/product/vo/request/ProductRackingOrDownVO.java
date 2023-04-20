package com.bosen.product.vo.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * 商品上/下架请求参数
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/19
 */
@Data
public class ProductRackingOrDownVO implements Serializable {
    private static final long serialVersionUID = -3362076186496528559L;

    /**
     * 上架商品id
     */
    @NotEmpty(message = "上架商品id不能为空")
    private List<String> productIds;

    @NotEmpty(message = "上架商城与店铺id不能为空")
    private List<StoreShopVO> storeShopList;
}
