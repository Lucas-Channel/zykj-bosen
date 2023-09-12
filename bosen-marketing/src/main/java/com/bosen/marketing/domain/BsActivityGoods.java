package com.bosen.marketing.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 活动商品表(BsActivityGoods)表实体类
 *
 * @author Lucas
 * @since 2023-09-12 13:52:40
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("bs_activity_goods")
public class BsActivityGoods extends BaseEntityData implements Serializable {

        private static final long serialVersionUID = -2439885898713016221L;


        /**
    * 活动id
    **/
        private String activityId;

        /**
    * 活动归属类型：1、平台，2、商家
    **/
        private Integer activityBelongType;

        /**
    * 商家报名id
    **/
        private String activityMerchantApplyId;

        /**
    * 商家id
    **/
        private String merchantId;

        /**
    * 商家角色id
    **/
        private String merchantRoleId;

        /**
    * 商家名称
    **/
        private String merchantName;

        /**
    * 商品spuId
    **/
        private String spuId;

        /**
    * 商品skuId
    **/
        private String skuId;

        /**
    * 商品spuName
    **/
        private String spuName;

        /**
    * 商品skuName
    **/
        private String skuName;

        /**
    * 品类id：当活动归属为平台，该值为平台品类，否则为商家品类
    **/
        private String categoryId;

        /**
    * 品类名称
    **/
        private String categoryName;

        /**
    * 品牌id
    **/
        private String brandId;

        /**
    * 品牌名称
    **/
        private String brandName;

        /**
    * 单位
    **/
        private String unit;

        /**
    * 原价
    **/
        private Double originalPrice;

        /**
    * 销售价
    **/
        private Double salesPrice;

        /**
    * 个人限购数量
    **/
        private Double restrictNum;

        /**
    * 活动库存
    **/
        private Double activityStock;

        /**
    * 销量
    **/
        private Double salesNum;

        /**
    * 展示图
    **/
        private String showImgUrl;

        /**
    * 审核状态：0，待审核，1，审核通过，2、审核不通过
    **/
        private Integer status;
}

