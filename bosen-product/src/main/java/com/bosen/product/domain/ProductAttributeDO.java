package com.bosen.product.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品规格/属性实体,一个商品对应多个属性，多个规格，一个规格对应多个规格值
 */
@Data
@TableName("bs_product_attribute")
public class ProductAttributeDO extends BaseEntityData implements Serializable {

    private static final long serialVersionUID = 4613708100451207664L;
    /**
     * 商品ID
     */
    private String productId;

    /**
     * 属性ID,当使用平台规格/属性时，该值必填
     */
    private String attributeId;

    /**
     * 属性名称
     */
    private String name;

    /**
     * 属性类型(1:规格;2:属性;)
     */
    private Integer type;

    /**
     * 排序
     */
    private Integer sort;

}
