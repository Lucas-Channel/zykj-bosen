package com.bosen.product.vo.request;

import lombok.Data;

import java.util.List;

@Data
public class ProductAttributeUpsertVO {

    private String id;

    private String productId;

    private String attributeId;

    private String name;

    /**
     * 属性类型(1:规格;2:属性;)
     */
    private Integer type;

    private List<ProductAttributeValueUpsertVO> value;

    private String picUrl;

}
