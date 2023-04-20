package com.bosen.product.vo.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/20
 */
@Data
public class ProductStoreShopDetailVO implements Serializable {
    private static final long serialVersionUID = 4955203904090644378L;

    private String storeId;

    private String shopId;

    private String storeName;

    private String shopName;
}
