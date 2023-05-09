package com.bosen.elasticsearch.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 商品销售区域
 * @author Lucas
 * @version 2.0.0
 * @date 2023/5/9
 */
@Data
public class EsProductSalesAreaDO implements Serializable {

    private static final long serialVersionUID = 8431578609838944306L;

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
