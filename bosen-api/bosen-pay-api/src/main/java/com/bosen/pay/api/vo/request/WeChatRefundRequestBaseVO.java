package com.bosen.pay.api.vo.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/6/13
 */
@Data
public class WeChatRefundRequestBaseVO implements Serializable {

    private static final long serialVersionUID = 8354570732442009192L;
    /**
     * 原支付交易对应的微信支付订单号
     */
    @NotBlank(message = "微信订单号不能为空")
    private String transaction_id;
//   商户订单号 和 微信支付订单号 二选一即可
//    /**
//     * 商户订单号
//     */
//    @NotBlank(message = "商户订单号不能为空")
//    private String out_trade_no;

    /**
     * 商户退款单号
     */
    @NotBlank(message = "商户退款单号不能为空")
    private String out_refund_no;

    /**
     * 退款原因
     */
    private String reason;

    /**
     * 退款结果回调url
     */
    @NotBlank(message = "退款结果回调url不能为空")
    private String notify_url;

    /**
     * 退款金额信息
     */
    @NotNull(message = "退款金额信息不能为空")
    private WeChatRefundAmountRequestVO amount;
}
