package com.bosen.marketing.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 优惠券领取记录(BsCouponAssignment)表实体类
 *
 * @author Lucas
 * @since 2023-09-12 13:52:47
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("bs_coupon_assignment")
public class BsCouponAssignment extends BaseEntityData implements Serializable {


        private static final long serialVersionUID = 5325238650046066659L;

        /**
         * 领取码
        **/
        private String assignmentCode;

        /**
    * 优惠券id
    **/
        private String couponId;

        /**
    * 优惠券组id
    **/
        private String couponGroupId;

        /**
    * 领取
    **/
        private Integer qty;

        /**
    * 是否使用
    **/
        private Integer status;
}

