package com.bosen.product.vo.response;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 仓位库存调拨单明细对象
 *
 * @author Lucas
 * @since 2023-07-14 10:08:18
 */
@Data
public class SkuInventoryAllotVO implements Serializable {

    private static final long serialVersionUID = -6170214913528617390L;

    private String id;

    /**
     * 调拨单号
     */
    private String allotNo;

    /**
     * 调拨时间
     */
    private LocalDateTime allotTime;

    /**
     * 调拨单状态
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

