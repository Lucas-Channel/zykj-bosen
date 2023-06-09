package com.bosen.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import com.bosen.system.constants.FundModelEnums;
import com.bosen.system.constants.PayMethodParamCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 支付方式-参数配置
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/6
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("bs_pay_method_params")
public class PayMethodParamsDO extends BaseEntityData implements Serializable {

    private static final long serialVersionUID = 4916153864619189942L;

    private String payMethodId;

    /**
     * 参数编码
     * @see PayMethodParamCodeEnum
     */
    private String paramCode;

    /**
     * 参数值
     */
    private String paramVal;

    /**
     * 备注
     */
    private String paramRemark;

    /**
     * 资金归集方式
     * @see FundModelEnums
     */
    private Integer fundModel;

    /**
     * 当资金归集方式为商家直接到账 必填
     */
    private String merchantId;

    /**
     * 当资金归集方式为商家直接到账 必填
     */
    private String merchantRoleId;

}
