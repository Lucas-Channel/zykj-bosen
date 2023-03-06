package com.bosen.product.vo.response;

import com.bosen.common.domain.BaseEntityData;
import lombok.Data;


/**
 * 商品默认分类属性/规格
 */
@Data
public class ProductCategoryAttributeDetailVO extends BaseEntityData {

    /**
     * 商品分类ID
     */
    private Long categoryId;

    /**
     * 属性/规格名称
     */
    private String name;

    /**
     * 类型(1:规格;2:属性;)
     */
    private Integer type;
}
