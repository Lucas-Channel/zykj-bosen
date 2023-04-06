package com.bosen.product.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;

import java.io.Serializable;

/**
 * 商家品类
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/6
 */
@Data
@TableName("bs_merchant_category")
public class MerchantCategoryDO extends BaseEntityData implements Serializable {
    private static final long serialVersionUID = -7443245159120188966L;

    private String name;

    private Long parentId;

    private String iconUrl;

    private Integer level;

    private Integer sort;
}
