package com.bosen.product.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品规格/属性 值
 */
@Data
@TableName("bs_product_attribute_value")
public class ProductAttributeValueDO extends BaseEntityData implements Serializable {

    private static final long serialVersionUID = 923761090154282826L;

    /**
     * 商品ID
     */
    private String productId;
    /**
     * 属性/规格ID
     */
    private String productAttributeId;

    /**
     * 属性/规格值
     */
    private String value;
    /**
     * 类型(1:规格;2:属性;)
     */
    private Integer type;

    /**
     * 规格图片地址
     */
    private String picUrl;

    /**
     * 是否默认属性/规格值
     */
    private Integer defaultAttributeValue;

    /**
     * 排序
     */
    private Integer sort;

}
