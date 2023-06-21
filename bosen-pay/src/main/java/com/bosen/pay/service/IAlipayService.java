package com.bosen.pay.service;

import com.bosen.common.constant.response.ResponseData;
import com.bosen.pay.api.vo.request.alipay.AlipayScanCodePayRequestVO;

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
    ResponseData<String> scanCodeToPay(AlipayScanCodePayRequestVO payRequestVO);

    /**
     * 扫码支付，面对面扫码支付
     * @return 二维码链接内容
     */
    ResponseData<String> scanCodeFaceToFaceToPay(AlipayScanCodePayRequestVO payRequestVO);

    /**
     * app支付
     * @return sdk链接内容
     */
    ResponseData<String> appToPay(AlipayScanCodePayRequestVO payRequestVO);

    /**
     * mobile web支付
     * @return 链接内容
     */
    ResponseData<String> mobileWebToPay(AlipayScanCodePayRequestVO payRequestVO);

}
