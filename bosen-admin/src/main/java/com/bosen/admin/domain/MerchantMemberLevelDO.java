package com.bosen.admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 商家-消费会员等级
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("bs_merchant_member_level")
public class MerchantMemberLevelDO extends BaseEntityData implements Serializable {
    private static final long serialVersionUID = 7260701693932404387L;

    private Long merchantId;

    private String name;
}
