package com.bosen.product.vo.response;

import com.bosen.common.domain.BaseEntityData;
import lombok.Data;

import java.util.List;

@Data
public class ProductCategoryWithBrandVO extends BaseEntityData {

    /**
     * 分类ID
     */
    private Long categoryId;

    private Long categoryName;

    private List<ProductCategoryBrandDetailVO> brandList;
}
