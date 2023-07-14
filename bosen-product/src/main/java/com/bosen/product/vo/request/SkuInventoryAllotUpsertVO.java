package com.bosen.product.vo.request;


import com.bosen.product.constant.InventoryAllotTypeEnum;
import com.bosen.product.constant.SkuInventoryAllotStatusEnum;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 仓位库存调拨新增/修改实体对象
 *
 * @author Lucas
 * @since 2023-07-14 10:08:18
 */
@Data
public class SkuInventoryAllotUpsertVO implements Serializable {

    private static final long serialVersionUID = 2717494392995168985L;

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
     * 调拨类型
     * @see InventoryAllotTypeEnum
     */
    @NotNull(message = "调拨类型不能为空")
    private Integer allotType;

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

    @NotEmpty(message = "调拨明细不能为空")
    private List<SkuInventoryAllotDetailVO> detail;

    /**
     * 是否保存并提交
     */
    private Boolean saveAndConfirm = false;
}

