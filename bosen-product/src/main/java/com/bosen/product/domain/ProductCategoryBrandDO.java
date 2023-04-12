package com.bosen.product.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;

/**
 * 平台分类品牌：一个分类多个品牌
 */
@Data
@TableName("bs_product_category_brand")
public class ProductCategoryBrandDO extends BaseEntityData {
    /**
     * 分类ID
     */
    private String categoryId;

    /**
     * 品牌ID
     */
    private String brandId;

}
