package com.bosen.product.vo.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 商品销售区域
 * @author Lucas
 * @version 2.0.0
 */
@Data
public class ProductAreaUpsertVO implements Serializable {

    private static final long serialVersionUID = 8687297719255134748L;

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
    private Integer allowAllCity;

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
    private Integer allowAllRegion;

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
    private Integer allowAllCommunity;

    /**
     * 小区编码
     */
    private String communityCode;

    /**
     * 小区名称
     */
    private String communityName;
}
