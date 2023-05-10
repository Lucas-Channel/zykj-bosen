package com.bosen.product.vo.request;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.bosen.product.constant.DeliveryTypeEnum;
import com.bosen.product.constant.FreightCalculateModelEnum;
import com.bosen.product.constant.ProductTypeEnums;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * 商品表单对象
 */
@Data
public class ProductUpsertVO {

    private String id;

    /**
     * spu 名称
     */
    @NotEmpty(message = "spu 名称不能为空")
    private String name;

    /**
     * 平台品类
     */
    @NotEmpty(message = "平台品类不能为空")
    private String categoryId;

    /**
     * 商家品类id
     */
    private String merchantCategoryId;

    /**
     * 品牌
     */
    @NotEmpty(message = "品牌不能为空")
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
    private BigDecimal salesCounts;

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
    @NotEmpty(message = "单位不能为空")
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
    @NotNull(message = "商品类型不能为空")
    private Integer productType;

    /**
     * 运费计算模式: 1,卖家承担运费，2，买家承担运费
     * @see FreightCalculateModelEnum
     */
    @NotNull(message = "运费计算模式不能为空")
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
     * 是否全球可售
     */
    private Integer salesAllArea;

    /**
     * 配送方式
     * @see DeliveryTypeEnum
     */
    @NotNull(message = "配送方式不能为空")
    private Integer deliveryType;

    @NotEmpty(message = "属性不能为空")
    private List<ProductAttributeUpsertVO> attrList;

    @NotEmpty(message = "规格不能为空")
    private List<ProductAttributeUpsertVO> specList;

    /**
     * 如果salesAllArea = 0，必填
     */
    private List<ProductAreaUpsertVO> areaList;

//    @NotEmpty
//    private List<ProductSkuUpsertVO> skuList;
}
