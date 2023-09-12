package com.bosen.marketing.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 优惠券(BsCoupon)表实体类
 *
 * @author Lucas
 * @since 2023-09-12 13:52:47
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("bs_coupon")
public class BsCoupon extends BaseEntityData implements Serializable {

        private static final long serialVersionUID = 2687543111318566435L;

        /**
    * 优惠券编码
    **/
        private String couponCode;

        /**
    * 优惠券名称
    **/
        private String couponName;

        /**
    * 优惠券类型：平台优惠券，店铺优惠券
    **/
        private Integer couponType;

        /**
    * 优惠层面：订单优惠，商品优惠，品牌优惠，品类优惠，通用优惠
    **/
        private Integer couponLevel;

        /**
    * 有效期起
    **/
        private LocalDateTime effectiveStartDate;

        /**
    * 有效期止
    **/
        private LocalDateTime effectiveEndDate;

        /**
    * 优惠券发放数量
    **/
        private Integer qty;

        /**
    * 最大领取数量
    **/
        private Integer maxPullQty;

        /**
    * 数量限制：不限领取，限制领取
    **/
        private Integer qtyLimitType;

        /**
    * 发放方式：主动领取，支付购买后发放，玩法领取，固定发放
    **/
        private Integer releaseModel;

        /**
    * 店铺id
    **/
        private String storeId;

        /**
    * 是否发放
    **/
        private Integer status;
}

