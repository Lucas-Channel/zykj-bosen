package com.bosen.product.vo.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 商品规格/属性VO
 */
@Data
public class ProductAttributeDetailVO implements Serializable {
    private static final long serialVersionUID = -468004000929862857L;

    private String id;

    private String productId;

    private String attributeId;

    private String name;

    /**
     * 属性类型(1:规格;2:属性;)
     */
    private Integer type;

    private List<ProductAttributeValueDetailVO> value;

    private String picUrl;

}
