package com.bosen.product.vo.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 商品规格/属性 值
 */
@Data
public class ProductAttributeValueUpsertVO implements Serializable {


    private static final long serialVersionUID = 1938334666866013546L;

    private String id;
    /**
     * 商品ID
     */
    private String productId;
    /**
     * 属性ID
     */
    private String productAttributeId;

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
