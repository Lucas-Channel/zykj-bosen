package com.bosen.product.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 仓位库存调拨明细(BsSkuInventoryAllotDetail)表实体类
 *
 * @author Lucas
 * @since 2023-07-14 10:08:18
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("bs_sku_inventory_allot_detail")
public class BsSkuInventoryAllotDetailDO extends BaseEntityData implements Serializable {

    private static final long serialVersionUID = 2476196402644780160L;

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

