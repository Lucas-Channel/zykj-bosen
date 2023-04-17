package com.bosen.product.vo.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 分类品牌：一个分类多个品牌
 */
@Data
public class ProductCategoryBrandDetailVO implements Serializable {
    private static final long serialVersionUID = -3194522499719621771L;
    private String id;
    /**
     * 分类ID
     */
    private Long categoryId;

    private Long categoryName;

    /**
     * 品牌ID
     */
    private Long brandId;

    private Long brandName;

}
