package com.bosen.product.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品包明细
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/1
 */
@Data
@TableName(value = "bs_product_package_detail", autoResultMap = true)
public class ProductPackageDetailDO extends BaseEntityData implements Serializable {

    private static final long serialVersionUID = 8091880796070603343L;
    /**
     * spuId
     */
    private String productId;

    /**
     * skuId
     */
    private String skuId;

}
