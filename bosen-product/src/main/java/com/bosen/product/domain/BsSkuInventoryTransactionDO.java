package com.bosen.product.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import com.bosen.product.constant.InventoryTransactionTypeConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * sku库存事务流水(BsSkuInventoryTransaction)表实体类
 *
 * @author Lucas
 * @since 2023-07-17 13:55:11
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("bs_sku_inventory_transaction")
public class BsSkuInventoryTransactionDO extends BaseEntityData implements Serializable {
    private static final long serialVersionUID = -1146715798262452496L;
    /**
     * skuId
     */
    private String skuId;
    /**
     * sku名称
     */
    private String skuName;
    /**
     * 仓位id
     */
    private String freightSpaceId;
    /**
     * 批次号
     */
    private String lotNumber;
    /**
     * 事务处理来源键值,记录来源表主键值.
     */
    private String transactionSourceKey;

    /**
     * 事务处理类型
     * @see InventoryTransactionTypeConstants
     */
    private Integer transactionType;
    /**
     * 数量
     */
    private Double quantity;
    /**
     * 说明
     */
    private String remark;
}

