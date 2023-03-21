package com.bosen.product.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/1
 */
@Data
@TableName(value = "bs_product", autoResultMap = true)
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
     * 标签id
     */
    private Long labelId;

    /**
     * 标签名称
     */
    private Long labelName;


}
