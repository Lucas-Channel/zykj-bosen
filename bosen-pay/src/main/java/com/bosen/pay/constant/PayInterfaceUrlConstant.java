package com.bosen.pay.constant;

/**
 * 支付接口明细管理静态变量
 * @author Lucas
 * @version 2.0.0
 * @date 2023/6/7
 */
public class PayInterfaceUrlConstant {
    /**
     * 微信支付-native支付
     */
    public static final String WE_CHAT_NATIVE_PAY = "/v3/pay/transactions/native";

    /**
     * 平台证书
     */
    public static final String CERTIFICATES = "/v3/certificates";

    /**
     * 商户可以通过查询订单接口主动查询订单状态，完成下一步的业务逻辑
     */
    public static final String QUERY_ORDER_STATUS = "/v3/pay/transactions/out-trade-no/%s";



}
