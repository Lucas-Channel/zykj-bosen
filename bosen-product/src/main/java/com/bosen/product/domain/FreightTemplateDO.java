package com.bosen.product.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/4/7
 */
@Data
@TableName("bs_freight_template")
public class FreightTemplateDO extends BaseEntityData implements Serializable {
    private static final long serialVersionUID = 6769336283685402339L;

    /**
     * 模板名称
     */
    private String name;

    /**
     * 计价方式 1-按重量
     */
    private Integer pricingMode;

    /**
     * 运送方式 1-快递
     */
    private Integer transportMode;

    /**
     * 首重(KG)
     */
    private BigDecimal firstWeight;

    /**
     * 首重价格(元)
     */
    private BigDecimal firstWeightPrice;

    /**
     * 每增加重量(KG)
     */
    private BigDecimal incrementWeight;

    /**
     * 每增加价格(元)
     */
    private BigDecimal incrementPrice;

    /**
     * 运费说明
     */
    private String remark;

    /**
     * 状态 0-无效 1-有效
     * @see com.bosen.common.constant.common.YesOrNoConstant
     */
    private Integer status;


    /**
     * 所属商家
     */
    private String merchantId;

    /**
     * 商家名称
     */
    private String merchantName;

    /**
     * 所属商家角色id
     */
    private String merchantRoleId;
}
