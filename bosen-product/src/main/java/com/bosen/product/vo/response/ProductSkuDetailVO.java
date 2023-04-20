package com.bosen.product.vo.response;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.bosen.product.domain.ProductSkuMemberPriceDO;
import com.bosen.product.domain.ProductSkuWholesalePriceDO;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 商品sku明细
 */
@Data
public class ProductSkuDetailVO {
    private String id;
    /**
     * sku编码
     */
    private String skuCode;

    /**
     * SKU 名称，如果autoSpliceSpecForName=1，拼接规格值，否则使用自动输入方式
     */
    private String name;

    /**
     * 商品规格值拼接：华为p50 红色 16G 256G 6.7英寸
     */
    private String specNameVal;

    /**
     * 商品 ID
     */
    private String productId;

    /**
     * 规格ID，多个使用英文逗号(,)分割
     */
    private String specIds;

    /**
     * 规格属性值：颜色：红；大小：200；内存：16G
     */
    private String specNames;

    /**
     * 原价
     */
    private BigDecimal originPrice;

    /**
     * 销售/零售价
     */
    private BigDecimal salesPrice;

    /**
     * vip价格
     */
    private BigDecimal vipPrice;

    /**
     * 库存数量
     */
    private BigDecimal stockNum;

    /**
     * 锁定库存数量
     */
    private BigDecimal lockedStockNum;

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
    private BigDecimal salesCount;

    /**
     * 是否自动拼接规格值作为skuName
     */
    private Integer autoSpliceSpecForName;

    /**
     * 库存预警数量
     */
    private BigDecimal stockInventoryWarning;

    /**
     * 条形码/二维码
     */
    private String barCode;

    /**
     * 是否计算库存
     */
    private Integer calculateInventory;

    /**
     * 有效期开始时间
     */
    private LocalDateTime validityStartTime;

    /**
     * 有效期结束时间
     */
    private LocalDateTime validityEndTime;

    /**
     * 单位积分
     */
    private BigDecimal unitScore;

    /**
     * 最小起订
     */
    private BigDecimal minOrder;

    /**
     * 重量
     */
    private BigDecimal weight;

    /**
     * 仓位id
     */
    private String freightSpaceId;

    /**
     * 是否售罄
     */
    private Integer sellOut;

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

    private String productName;
}
