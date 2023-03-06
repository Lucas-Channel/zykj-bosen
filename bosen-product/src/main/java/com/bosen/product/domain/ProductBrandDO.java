package com.bosen.product.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;


@Data
@TableName("bs_product_brand")
public class ProductBrandDO extends BaseEntityData {

    private String name;

    private String logoUrl;

    private Integer sort;
}
