package com.bosen.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import com.bosen.system.constants.PayMethodParamCodeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 支付方式
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/6
 */
@Data
@TableName("bs_pay_method_params")
public class PayMethodParamsDO extends BaseEntityData implements Serializable {

    private static final long serialVersionUID = 4916153864619189942L;

    private Long payMethodId;

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

}
