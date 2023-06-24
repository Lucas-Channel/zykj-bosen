package com.bosen.pay.api.vo.request.alipay;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 支付宝-退款
 * @author Lucas
 * @version 2.0.0
 * @date 2023/6/23
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AlipayRefundRequestVO extends AlipayRequestBase implements Serializable {
    private static final long serialVersionUID = 2553198226308117178L;
    /**
     * 商户订单号。
     */
    private String out_trade_no;

    /**
     * 支付宝交易单号
     */
    private String trade_no;

    /**
     * 退款金额。
     * 单位为元，精确到小数点后两位
     */
    @NotNull(message = "退款金额不能为空")
    private BigDecimal refund_amount;
}
