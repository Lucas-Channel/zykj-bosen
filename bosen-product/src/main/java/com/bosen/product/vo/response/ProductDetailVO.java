package com.bosen.product.vo.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/1
 */
@Data
public class ProductDetailVO {

    private String id;

    private String name;
    /**
     * 品类
     */
    private Long categoryId;

    private String categoryName;

    /**
     * 品牌
     */
    private Long brandId;

    private String brandName;

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

    /**
     * 属性
     */
    private List<ProductCategoryAttributeDetailVO> attributeList;

    /**
     * 规格
     */
    private List<ProductCategoryAttributeDetailVO> specList;
}
