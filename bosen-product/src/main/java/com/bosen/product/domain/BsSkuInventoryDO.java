package com.bosen.product.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 商品SKU库存(BsSkuInventory)表实体类
 *
 * @author Lucas
 * @since 2023-07-14 10:08:18
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("bs_sku_inventory")
public class BsSkuInventoryDO extends BaseEntityData implements Serializable {

    private static final long serialVersionUID = -5165898903258489228L;

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

