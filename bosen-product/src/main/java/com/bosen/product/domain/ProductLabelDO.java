package com.bosen.product.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 商品标签
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/3
 */
@Data
@Accessors(chain = true)
@TableName("bs_product_label")
public class ProductLabelDO extends BaseEntityData implements Serializable {
    private static final long serialVersionUID = 3327077487675541212L;
    /**
     * 标签id
     */
    private String labelId;

    /**
     * 标签名称
     */
    private String labelName;

    /**
     * spuId
     */
    private String productId;
}
