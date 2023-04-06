package com.bosen.product.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.bosen.common.domain.BaseEntityData;
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

    private static final long serialVersionUID = 8966284598903261678L;
    /**
     * spu 名称
     */
    private String name;
    /**
     * 平台品类
     */
    private Long categoryId;

    /**
     * 商家品类id
     */
    private Long merchantCategoryId;

    /**
     * 品牌
     */
    private Long brandId;

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
     * 商品状态(0-下架 1-上架，2-待审核，3-审核不通过, 4-审核通过)
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
    private Long merchantRoleId;

    /**
     * 商品类型
     * @see ProductTypeEnums
     */
    private Integer productType;

}
