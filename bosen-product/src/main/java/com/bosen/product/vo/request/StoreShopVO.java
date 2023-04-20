package com.bosen.product.vo.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 上架店铺参数
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/20
 */
@Data
public class StoreShopVO implements Serializable {
    private static final long serialVersionUID = 5523896065577186776L;
    /**
     * 店铺id
     */
    @NotEmpty(message = "店铺id不能为空")
    private String storeId;

    /**
     * 商城id
     */
    @NotEmpty(message = "商城id不能为空")
    private String shopId;
}
