package com.bosen.product.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 特别说明：sku的价格是统一零售价格，如果存在指定会员价格或者批量价格，取其他表
 * 商品sku
 */
@Data
@TableName(value = "bs_product_sku", autoResultMap = true)
public class ProductSkuDO extends BaseEntityData {
    /**
     * sku编码
     */
    private String skuCode;

    /**
     * SKU 名称：拼接规格值
     */
    private String name;

    /**
     * 商品 ID
     */
    private Long productId;

    /**
     * 规格ID，多个使用英文逗号(,)分割
     */
    private String specIds;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * vip价格
     */
    private BigDecimal vipPrice;

    /**
     * 库存数量
     */
    private Integer stockNum;

    /**
     * 锁定库存数量
     */
    private Integer lockedStockNum;

    /**
     * 商品图片地址
     */
    private String picUrl;

    /**
     * 入库批次
     */
    private String lotNumber;

    /**
     * 批发价格，可能存在返回性价格
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<ProductSkuWholesalePriceDO> wholesalePrice;

    /**
     * 指定会员价格，用于临时
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<ProductSkuMemberPriceDO> memberPrice;

    /**
     * 销售数量
     */
    private Integer salesCount;
}
