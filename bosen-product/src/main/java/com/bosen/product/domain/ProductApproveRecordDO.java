package com.bosen.product.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 商品审核记录
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/12
 */
@Data
@TableName("bs_product_approve_record")
@Accessors(chain = true)
public class ProductApproveRecordDO extends BaseEntityData implements Serializable {
    private static final long serialVersionUID = -5852855119849039995L;

    /**
     * spuId
     */
    private String productId;

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
