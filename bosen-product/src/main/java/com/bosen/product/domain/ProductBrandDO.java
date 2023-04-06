package com.bosen.product.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;


/**
 * 平台品牌
 */
@Data
@TableName("bs_product_brand")
public class ProductBrandDO extends BaseEntityData {

    private String name;

    private String logoUrl;

    private Integer sort;
}
