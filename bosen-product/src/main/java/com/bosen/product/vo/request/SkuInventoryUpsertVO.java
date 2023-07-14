package com.bosen.product.vo.request;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 商品SKU库存修改/新增实体类
 *
 * @author Lucas
 * @since 2023-07-14 10:08:18
 */
@Data
public class SkuInventoryUpsertVO implements Serializable {

    private static final long serialVersionUID = -2142970693554485177L;

    private String id;

    /**
     * skuId
     */
    private String skuId;

    /**
     * 库存数量
     */
    private BigDecimal stockNum;

    /**
     * 锁定库存数量
     */
    private BigDecimal lockedStockNum;

    /**
     * 入库批次
     */
    private String lotNumber;

    /**
     * 仓位id
     */
    private String freightSpaceId;

    /**
     * 有效日期起
     */
    private LocalDateTime allotEffectiveStart;

    /**
     * 有效日期止
     */
    private LocalDateTime allotEffectiveEnd;

    /**
     * 备注
     */
    private String remark;

}

