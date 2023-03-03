package com.bosen.product.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;

/**
 * 商品规格/属性实体,一个商品对应多个属性，多个规格，一个规格对应多个规格值
 */
@Data
@TableName("bs_product_attribute")
public class ProductAttributeDO extends BaseEntityData {

    /**
     * 商品ID
     */
    private Long productId;
    /**
     * 属性ID
     */
    private Long attributeId;
    /**
     * 属性名称
     */
    private String name;

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

}
