package com.bosen.pay.service;

import com.bosen.common.constant.response.ResponseData;
import com.bosen.pay.api.vo.request.NativePayRequest;
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
     * 微信支付回调通知
     * @param body 参数
     * @param request 请求
     * @return 回调结果
     */
    WeChatCallbackVO payCallBack(Map<String, Object> body, HttpServletRequest request);
}
