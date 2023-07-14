package com.bosen.product.vo.request;

import com.bosen.common.domain.PageVO;
import com.bosen.product.constant.InventoryAllotTypeEnum;
import com.bosen.product.constant.SkuInventoryAllotStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/7/14
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SkuInventoryAllotQueryVO extends PageVO implements Serializable {
    private static final long serialVersionUID = 6580862392321017465L;
    /**
     * 调拨单号
     */
    private String allotNo;

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
}
