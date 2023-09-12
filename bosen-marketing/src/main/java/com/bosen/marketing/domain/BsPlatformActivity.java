package com.bosen.marketing.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 平台活动主表(BsPlatformActivity)表实体类
 *
 * @author Lucas
 * @since 2023-09-12 13:52:47
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("bs_platform_activity")
public class BsPlatformActivity extends BaseEntityData implements Serializable {


        private static final long serialVersionUID = 379667052260724781L;

        /**
    * 活动编码
    **/
        private String activityCode;

        /**
    * 活动名称
    **/
        private String activityName;

        /**
    * 活动开始时间
    **/
        private LocalDateTime activityStartTime;

        /**
    * 活动结束时间
    **/
        private LocalDateTime activityEndTime;

        /**
    * 是否允许使用优惠券
    **/
        private Integer allowUseCoupon;

        /**
    * 活动方式：1、商家报名参与。2、平台抽奖活动
    **/
        private Integer type;

        /**
    * 活动类型， 1-特价促销 2-直降促销 3-折扣促销 4-满量促销 5-满额促销 6-赠送促销 7-多件促销 8-组合促销 9-拼团 10-抽奖 11-砍价 12-秒杀 13-换购 14-预售 15-套餐 16-试用
    **/
        private Integer activityType;

        /**
    * 活动细分类型（满额、满量、赠送促销）：1.满量减/满额减/赠商品；2.满量折/满额折/赠优惠卷
    **/
        private Integer activityMinType;

        /**
    * 活动规则
    **/
        private Object activityRule;

        /**
    * 是否发布
    **/
        private Integer status;

}

