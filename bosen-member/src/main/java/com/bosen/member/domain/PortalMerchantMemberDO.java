package com.bosen.member.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.constant.common.YesOrNoConstant;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;

import java.io.Serializable;

/**
 * 商家会员绑定关系
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/19
 */
@Data
@TableName("bs_merchant_member_relation")
public class PortalMerchantMemberDO extends BaseEntityData implements Serializable {
    private static final long serialVersionUID = -4811396719999137781L;

    /**
     * C端用户id
     */
    private Long memberId;

    /**
     * 商家id
     */
    private Long merchantId;

    /**
     * 是否是商家会员
     * @see YesOrNoConstant
     */
    private Integer vipMember;

    /**
     * 是否是关注商家
     * @see YesOrNoConstant
     */
    private Integer attentionFlag;

    /**
     * 商家会员等级
     */
    private Long merchantMemberLevelId;
}
