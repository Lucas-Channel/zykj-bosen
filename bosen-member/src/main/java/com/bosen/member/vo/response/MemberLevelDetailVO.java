package com.bosen.member.vo.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 消费会员等级
 */
@Data
public class MemberLevelDetailVO implements Serializable {

    private static final long serialVersionUID = 6973254773202772321L;

    private Long id;

    /**
     * 等级名称
     */
    private String name;

    /**
     * 成长值
     */
    private Integer growthPoint;

    /**
     * 是否为默认等级：0->不是；1->是
     */
    private Integer defaultStatus;

    /**
     * 免运费标准
     */
    private BigDecimal freeFreightPoint;

    /**
     * 每次评价获取的成长值
     */
    private Integer commentGrowthPoint;

    /**
     * 是否有免邮特权
     */
    private Integer privilegeFreeFreight;

    /**
     * 是否有签到特权
     */
    private Integer privilegeSignIn;

    /**
     * 是否有评论获奖励特权
     */
    private Integer privilegeComment;

    /**
     * 是否有专享活动特权
     */
    private Integer privilegePromotion;

    /**
     * 是否有会员价格特权
     */
    private Integer privilegeMemberPrice;

    /**
     * 是否有生日特权
     */
    private Integer privilegeBirthday;

    private String note;

}