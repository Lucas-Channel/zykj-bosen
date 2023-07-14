package com.bosen.product.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import com.bosen.product.constant.InventoryAllotTypeEnum;
import com.bosen.product.constant.SkuInventoryAllotStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 仓位库存调拨(BsSkuInventoryAllot)表实体类
 *
 * @author Lucas
 * @since 2023-07-14 10:08:18
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("bs_sku_inventory_allot")
public class BsSkuInventoryAllotDO extends BaseEntityData implements Serializable {

    private static final long serialVersionUID = 9032196615193818412L;

    /**
     * 调拨单号
     */
    private String allotNo;

    /**
     * 调拨时间
     */
    private LocalDateTime allotTime;

    /**
     * 调拨类型
     * @see InventoryAllotTypeEnum
     */
    private LocalDateTime allotType;

    /**
     * 调拨单状态
     * @see SkuInventoryAllotStatusEnum
     */
    private Integer status;

    /**
     * 调拨总量
     */
    private BigDecimal allotQuantity;

    /**
     * 调拨费用
     */
    private BigDecimal allotAmount;

    /**
     * 调拨说明
     */
    private String allotRemark;

    /**
     * 附件地址，逗号拼接
     */
    private String allotFile;

    /**
     * 商品所属商家
     */
    private String merchantId;

    /**
     * 商家名称
     */
    private String merchantName;

    /**
     * 商品所属商家角色id
     */
    private String merchantRoleId;
}

