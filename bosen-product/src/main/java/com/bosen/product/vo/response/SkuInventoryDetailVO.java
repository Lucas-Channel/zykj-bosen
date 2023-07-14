package com.bosen.product.vo.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/7/14
 */
@Data
public class SkuInventoryDetailVO implements Serializable {
    private static final long serialVersionUID = 7674998888995072962L;

    private String id;

    /**
     * skuId
     */
    private String skuId;

    /**
     * 商品名称
     */
    private String skuName;

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
     * 仓位名称
     */
    private String freightSpaceName;

    /**
     * 有效日期起
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime allotEffectiveStart;

    /**
     * 有效日期止
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime allotEffectiveEnd;

    /**
     * 备注
     */
    private String remark;
}
