package com.bosen.product.vo.request;

import com.bosen.common.domain.PageVO;
import lombok.Data;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/2
 */
@Data
public class ProductCategoryAttributeQueryVO extends PageVO {
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
