package com.bosen.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import com.bosen.system.constants.FundModelEnums;
import lombok.Data;

import java.io.Serializable;

/**
 * 支付方式
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/6
 */
@Data
@TableName("bs_pay_method")
public class PayMethodDO extends BaseEntityData implements Serializable {

    private static final long serialVersionUID = 4916153864619189942L;

    /**
     * 支付方式编码
     */
    private String payMethodCode;

    /**
     * 支付方式名称
     */
    private String payMethodName;

    /**
     * 图标
     */
    private String iconUrl;

    /**
     * 是否开启
     */
    private Integer enableFlag;

    /**
     * 资金归集方式
     * @see FundModelEnums
     */
    private Integer fundModel;

}
