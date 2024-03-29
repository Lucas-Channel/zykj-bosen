package com.bosen.admin.vo.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 店铺明细
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/14
 */
@Data
public class StoreShopDetailVO implements Serializable {
    private static final long serialVersionUID = 7979130641535147637L;

    private String id;

    private String storeId;

    /**
     * 店铺名称
     */
    private String storeName;

    /**
     * 店铺logo
     */
    private String logoUrl;

    /**
     * 描述
     */
    private String remark;

    /**
     * 信誉分
     */
    private BigDecimal creditScore;

    /**
     * 上架商城id
     */
    private String shopId;

    /**
     * 上架商城名称
     */
    private String shopName;

    private Integer applyStatus;
}
