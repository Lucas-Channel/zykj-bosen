package com.bosen.admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 入驻商家-角色信息
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("bs_merchant_role_relation")
public class MerchantRoleRelationDO extends BaseEntityData implements Serializable {

    private static final long serialVersionUID = -8811559807205916088L;
    /**
     * 商户id
     */
    private Long merchantId;

    /**
     * 角色id
     */
    private Long roleId;
}
