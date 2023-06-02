package com.bosen.system.vo.response;

import com.bosen.system.constants.FundModelEnums;
import com.bosen.system.constants.PayMethodParamCodeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 支付方式-参数配置
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/6
 */
@Data
public class PayMethodParamsDetailVO implements Serializable {

    private static final long serialVersionUID = -5911428402388554746L;

    private String id;

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

}
