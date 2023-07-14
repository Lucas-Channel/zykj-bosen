package com.bosen.product.vo.request;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.bosen.product.domain.ProductSkuMemberPriceDO;
import com.bosen.product.domain.ProductSkuWholesalePriceDO;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * sku保存请求参数
 */
@Data
public class ProductSkuUpsertVO implements Serializable {
    private static final long serialVersionUID = 5876574978553367562L;
    private String id;
    /**
     * sku编码
     */
    @NotBlank(message = "sku编码不能为空")
    private String skuCode;

    /**
     * SKU 名称，如果autoSpliceSpecForName=1，拼接规格值，否则使用自动输入方式
     */
    private String name;

    /**
     * 商品规格值拼接：华为p50 红色 16G 256G 6.7英寸
     */
    @NotBlank(message = "商品规格值拼接内容不能为空")
    private String specNameVal;

    /**
     * 商品 ID
     */
    @NotBlank(message = "商品Id不能为空")
    private String productId;

    /**
     * 规格ID，多个使用英文逗号(,)分割
     */
    @NotBlank(message = "规格ID不能为空")
    private String specIds;

    /**
     * 规格属性值：颜色：红；大小：200；内存：16G
     */
    @NotBlank(message = "规格属性值不能为空")
    private String specNames;

    /**
     * 原价
     */
    @NotNull(message = "原价不能为空")
    private BigDecimal originPrice;

    /**
     * 销售/零售价
     */
    @NotNull(message = "销售/零售价不能为空")
    private BigDecimal salesPrice;

    /**
     * vip价格
     */
    @NotNull(message = "vip价格不能为空")
    private BigDecimal vipPrice;

    /**
     * 商品主图-图片地址
     */
    private String skuImg;

    /**
     * 商品sku轮播图
     */
    private String[] album;

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
    @NotNull(message = "是否自动拼接规格值作为skuName不能为空")
    private Integer autoSpliceSpecForName = 1;

    /**
     * 库存预警数量
     */
    @NotNull(message = "库存预警数量不能为空")
    private BigDecimal stockInventoryWarning;

    /**
     * 条形码/二维码
     */
    private String barCode;

    /**
     * 是否计算库存
     */
    @NotNull(message = "是否计算库存不能为空")
    private Integer calculateInventory;

    /**
     * 单位积分
     */
    private BigDecimal unitScore;

    /**
     * 最小起订
     */
    @NotNull(message = "最小起订不能为空")
    private BigDecimal minOrder;

    /**
     * 重量
     */
    @NotNull(message = "重量不能为空")
    private BigDecimal weight;

    /**
     * 是否售罄
     */
    private Integer sellOut = 0;

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
}
