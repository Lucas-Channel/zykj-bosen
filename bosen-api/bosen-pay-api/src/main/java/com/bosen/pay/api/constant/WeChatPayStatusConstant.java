package com.bosen.pay.api.constant;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/6/13
 */
public class WeChatPayStatusConstant {

    /**
     * 支付成功
     */
    public static final String SUCCESS = "SUCCESS";
    /**
     * 转入退款
     */
    public static final String REFUND = "REFUND";
    /**
     * 未支付
     */
    public static final String NOT_PAY = "NOTPAY";
    /**
     * 已关闭
     */
    public static final String CLOSED = "CLOSED";
    /**
     * 已撤销（付款码支付）
     */
    public static final String REVOKED = "REVOKED";
    /**
     * 用户支付中（付款码支付）
     */
    public static final String USER_PAYING = "USERPAYING";
    /**
     * 支付失败(其他原因，如银行返回失败)
     */
    public static final String PAY_ERROR = "PAYERROR";
}
