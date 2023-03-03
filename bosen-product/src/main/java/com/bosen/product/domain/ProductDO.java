package com.bosen.product.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/1
 */
@Data
@TableName("bs_product")
public class ProductDO extends BaseEntityData {
    /**
     * 品类
     */
    private Long categoryId;

    /**
     * 品牌
     */
    private Long brandId;

    /**
     * 原价
     */
    private BigDecimal originPrice;

    /**
     * 销售/零售价
     */
    private BigDecimal salesPrice;

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
     * 商品状态(0:下架 1:上架)
     */
    private Integer status;
}
