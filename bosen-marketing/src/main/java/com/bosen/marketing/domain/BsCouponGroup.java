package com.bosen.marketing.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 优惠券组(BsCouponGroup)表实体类
 *
 * @author Lucas
 * @since 2023-09-12 13:52:47
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("bs_coupon_group")
public class BsCouponGroup extends BaseEntityData implements Serializable {


        private static final long serialVersionUID = -8143634219893362735L;
        /**
    * 优惠券组编码
    **/
        private String couponGroupCode;

        /**
    * 优惠券组名称
    **/
        private String couponGroupName;

        /**
    * 优惠券id,逗号拼接
    **/
        private String couponIds;

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
    * 是否发放
    **/
        private Integer status;
}

