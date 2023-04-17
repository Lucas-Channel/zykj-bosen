package com.bosen.product.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;

/**
 * 平台品类
 */
@Data
@TableName("bs_product_category")
public class ProductCategoryDO extends BaseEntityData {

    private String name;

    private String parentId;

    private String iconUrl;

    private Integer level;

    private Integer sort;
}
