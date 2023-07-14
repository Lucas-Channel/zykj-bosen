package com.bosen.product.vo.request;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 仓位库存调拨明细新增
 *
 * @author Lucas
 * @since 2023-07-14 10:08:18
 */
@Data
public class SkuInventoryAllotDetailVO implements Serializable {


    private static final long serialVersionUID = 8779401498985687957L;

    /**
     * 调拨单id
     */
    private String allotId;

    /**
     * 调出仓位id
     */
    private String outFreightSpaceId;

    /**
     * 调入仓位id
     */
    private String inFreightSpaceId;

    /**
     * 商品skuId
     */
    private String skuId;

    /**
     * 费用价
     */
    private BigDecimal amount;

    /**
     * 数量
     */
    private BigDecimal quantity;

    /**
     * 入库批次
     */
    private String lotNumber;

    /**
     * 说明
     */
    private String remark;

}

