package com.bosen.product.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;

@Data
@TableName("bs_product_category")
public class ProductCategory extends BaseEntityData {

    private String name;

    private Long parentId;

    private String iconUrl;

    private Integer level;

    private Integer sort;
}
