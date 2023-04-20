package com.bosen.product.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.bosen.common.constant.common.YesOrNoConstant;
import com.bosen.common.domain.BaseEntityData;
import com.bosen.product.constant.DeliveryTypeEnum;
import com.bosen.product.constant.FreightCalculateModelEnum;
import com.bosen.product.constant.ProductApproveStatusEnum;
import com.bosen.product.constant.ProductTypeEnums;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/1
 */
@Data
@TableName(value = "bs_product", autoResultMap = true)
public class ProductDO extends BaseEntityData implements Serializable {

    private static final long serialVersionUID = 6061006344740033949L;
    /**
     * spu 名称
     */
    private String name;
    /**
     * 平台品类，id通过逗号拼接，完整目录
     */
    private String categoryId;

    /**
     * 商家品类id，id通过逗号拼接，完整目录
     */
    private String merchantCategoryId;

    /**
     * 品牌
     */
    private String brandId;

    /**
     * 原价，默认规格价格
     */
    private BigDecimal originPrice;

    /**
     * 销售/零售价，默认规格价格
     */
    private BigDecimal salesPrice;

    /**
     * 卖点
     */
    private String sellingPoint;

    /**
     * 销量
     */
    private Integer salesCounts;

    /**
     * 商品主图
     */
    private String picUrl;

    /**
     * 商品轮播图
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private String[] album;

    /**
     * 单位
     */
    private String unit;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 详情
     */
    private String detail;

    /**
     * 商品状态(0-待提交审核，1-待审核，2-审核不通过, 3-审核通过,4-下架 5-上架，)
     * @see ProductApproveStatusEnum
     */
    private Integer status;

    /**
     * 上架时间
     */
    private LocalDateTime pushDateTime;

    /**
     * 下架时间
     */
    private LocalDateTime pullDateTime;

    /**
     * 审核日期
     */
    private LocalDateTime applyDateTime;

    /**
     * 商品所属商家
     */
    private String merchantId;

    /**
     * 商家名称
     */
    private String merchantName;

    /**
     * 商品所属商家角色id
     */
    private String merchantRoleId;

    /**
     * 商品类型
     * @see ProductTypeEnums
     */
    private Integer productType;

    /**
     * 运费计算模式: 1,卖家承担运费，2，买家承担运费
     * @see FreightCalculateModelEnum
     */
    private Integer freightCalculateModel;

    /**
     * 运费模板
     */
    private String freightTemplateId;

    /**
     * 物流公司id
     */
    private String deliveryCompanyId;

    /**
     * 配送方式
     * @see DeliveryTypeEnum
     */
    private Integer deliveryType;

    /**
     * 是否全国可售
     * @see YesOrNoConstant
     */
    private Integer salesAllArea = 1;
//
//    /**
//     * 店铺id
//     */
//    private String storeId;
//
//    /**
//     * 店铺名称
//     */
//    private String storeName;
//
//    /**
//     * 店铺头像
//     */
//    private String storeLogo;

}
