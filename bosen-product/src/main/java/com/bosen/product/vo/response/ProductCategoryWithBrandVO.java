package com.bosen.product.vo.response;

import com.bosen.common.domain.BaseEntityData;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProductCategoryWithBrandVO extends BaseEntityData implements Serializable {

    private static final long serialVersionUID = 5971949277551946621L;
    /**
     * 分类ID
     */
    private String categoryId;

    private String categoryName;

    private List<ProductCategoryBrandDetailVO> brandList;
}
