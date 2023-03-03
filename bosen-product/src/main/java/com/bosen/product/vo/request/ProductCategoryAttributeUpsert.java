package com.bosen.product.vo.request;

import com.bosen.common.domain.BaseEntityData;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * 商品默认分类属性/规格
 */
@Data
public class ProductCategoryAttributeUpsert extends BaseEntityData {

    /**
     * 商品分类ID
     */
    @NotNull(message = "商品分类ID不能为空")
    private Long categoryId;

    /**
     * 属性/规格名称
     */
    @NotBlank(message = "属性/规格名称不能为空")
    private String name;

    /**
     * 类型(1:规格;2:属性;)
     */
    @NotNull(message = "类型不能为空")
    private Integer type;
}
