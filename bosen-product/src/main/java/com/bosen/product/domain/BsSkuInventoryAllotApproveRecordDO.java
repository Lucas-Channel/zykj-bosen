package com.bosen.product.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 调拨单审核记录(BsSkuInventoryAllotApproveRecord)表实体类
 *
 * @author Lucas
 * @since 2023-07-14 16:03:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("bs_sku_inventory_allot_approve_record")
public class BsSkuInventoryAllotApproveRecordDO extends BaseEntityData implements Serializable {

    private static final long serialVersionUID = -8379937500993435785L;

    /**
     * 调拨单id
     */
    private String allotId;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 操作用户id
     */
    private String operationUserId;

    /**
     * 操作用户角色id
     */
    private String operationUserRoleId;

    /**
     * 操作用户名称
     */
    private String operationUserName;

    /**
     * 审核意见
     */
    private String agreeAdvice;
}

