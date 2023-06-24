package com.bosen.pay.service;

import com.bosen.common.constant.response.ResponseData;
import com.bosen.pay.api.vo.request.alipay.AlipayRefundRequestVO;
import com.bosen.pay.api.vo.request.alipay.AlipayRequestVO;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 支付宝支付-接口
 * @author Lucas
 * @version 2.0.0
 * @date 2023/6/19
 */
public interface IAlipayService {
    /**
     * 扫码支付，电脑支付
     * @return form表单
     */
    ResponseData<String> scanCodeToPay(AlipayRequestVO payRequestVO);

    /**
     * 扫码支付，面对面扫码支付
     * @return 二维码链接内容
     */
    ResponseData<String> scanCodeFaceToFaceToPay(AlipayRequestVO payRequestVO);

    /**
     * app支付
     * @return sdk链接内容
     */
    ResponseData<String> appToPay(AlipayRequestVO payRequestVO);

    /**
     * mobile web支付
     * @return 链接内容
     */
    ResponseData<String> mobileWebToPay(AlipayRequestVO payRequestVO);

    /**
     * 统一退款接口
     * @param refundRequestVO 参数
     * @return 结果
     */
    ResponseData<Void> refund(AlipayRefundRequestVO refundRequestVO);

    String payCallBack(Map<String, Object> body, HttpServletRequest request);

}
