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
    private Long productId;
    /**
     * 属性ID
     */
    private Long productAttributeId;

    /**
     * 属性值
     */
    private String value;
    /**
     * 属性类型(1:规格;2:属性;)
     */
    private Integer type;

    /**
     * 规格图片地址
     */
    private String picUrl;

    /**
     * 是否默认属性值
     */
    private Integer defaultAttributeValue;

    /**
     * 排序
     */
    private Integer sort;

}
