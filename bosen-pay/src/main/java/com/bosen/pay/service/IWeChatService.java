package com.bosen.pay.service;

import com.bosen.common.constant.response.ResponseData;
import com.bosen.pay.api.vo.request.wechat.h5.H5PayRequest;
import com.bosen.pay.api.vo.request.wechat.nativepay.NativePayRequest;
import com.bosen.pay.api.vo.request.wechat.WeChatRefundRequestVO;
import com.bosen.pay.vo.response.WeChatCallbackVO;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 微信支付相关接口
 * @author Lucas
 * @version 2.0.0
 * @date 2023/6/5
 */
public interface IWeChatService {
    /**
     * native支付，用于pc端支付，用于扫码支付
     * @param nativePayRequest 参数
     * @return 支付链接
     */
    ResponseData<String> nativePay(NativePayRequest nativePayRequest);

    /**
     * h5支付
     * @param h5PayRequest 参数
     * @return 支付链接
     */
    ResponseData<String> h5PayRequest(H5PayRequest h5PayRequest);

    /**
     * 微信支付回调通知
     * @param body 参数
     * @param request 请求
     * @return 回调结果
     */
    WeChatCallbackVO payCallBack(Map<String, Object> body, HttpServletRequest request);

    /**
     * 微信退款回调通知
     * @param body 参数
     * @param request httpRequest
     * @return 回调处理结果
     */
    WeChatCallbackVO refundNotify(Map<String, Object> body, HttpServletRequest request);

    /**
     * 主动查询订单支付结果
     * @param mchId 商户号
     * @param outTradeNo 交易单号
     * @return true支付成功，false支付失败
     */
    ResponseData<Boolean> queryOrderPayStatus(String mchId, String outTradeNo);

    /**
     * 退款申请
     * @param refundRequestVO 参数
     * @return 结果
     */
    ResponseData<Void> refund(WeChatRefundRequestVO refundRequestVO);
}
