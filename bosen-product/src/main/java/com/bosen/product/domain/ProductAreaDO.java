package com.bosen.product.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品销售区域
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/20
 */
@Data
@TableName("bs_product_area")
public class ProductAreaDO extends BaseEntityData implements Serializable {
    private static final long serialVersionUID = -3078678844870510362L;

    private String productId;

    /**
     * 省级编码
     */
    private String provinceCode;

    /**
     * 省级名称
     */
    private String provinceName;

    /**
     * 是否不限制城市
     */
    private Integer allowAllCity = 1;

    /**
     * 市级编码
     */
    private String cityCode;

    /**
     * 市级名称
     */
    private String cityName;

    /**
     * 是否不限制区域
     */
    private Integer allowAllRegion = 1;

    /**
     * 区域编码
     */
    private String regionCode;

    /**
     * 区域名称
     */
    private String regionName;

    /**
     * 是否不限制小区
     */
    private Integer allowAllCommunity = 1;

    /**
     * 小区编码
     */
    private String communityCode;

    /**
     * 小区名称
     */
    private String communityName;
}
