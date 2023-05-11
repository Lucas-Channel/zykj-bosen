package com.bosen.search.vo.request;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/5/10
 */
@Data
public class UpdateSkuSalesCountVO implements Serializable {
    private static final long serialVersionUID = 7191210322575448331L;

    private BigDecimal salesCount;

    private String skuId;

    private String shopId;

    private String storeId;
}
