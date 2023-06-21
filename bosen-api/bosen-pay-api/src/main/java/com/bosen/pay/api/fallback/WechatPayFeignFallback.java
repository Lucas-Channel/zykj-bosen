package com.bosen.pay.api.fallback;

import com.bosen.common.constant.response.ResponseCode;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.pay.api.feign.IWechatPayFeign;
import com.bosen.pay.api.vo.request.wechat.WeChatRefundRequestVO;
import com.bosen.pay.api.vo.request.wechat.h5.H5PayRequestVO;
import com.bosen.pay.api.vo.request.wechat.jsapi.JsApiPayRequestVO;
import com.bosen.pay.api.vo.request.wechat.nativepay.NativePayRequestVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/25
 */
@Slf4j
@Component
public class WechatPayFeignFallback implements IWechatPayFeign {

    @Override
    public ResponseData<String> nativePay(NativePayRequestVO nativePayRequestVO) {
        return ResponseData.fail(ResponseCode.WECHAT_REQUEST_ERROR);
    }

    @Override
    public ResponseData<String> h5PayRequest(H5PayRequestVO h5PayRequestVO) {
        return ResponseData.fail(ResponseCode.WECHAT_REQUEST_ERROR);
    }

    @Override
    public ResponseData<String> jsApiPayRequest(JsApiPayRequestVO jsApiPayRequestVO) {
        return ResponseData.fail(ResponseCode.WECHAT_REQUEST_ERROR);
    }

    @Override
    public ResponseData<Boolean> queryOrderPayStatus(String mchId, String outTradeNo) {
        return ResponseData.fail(ResponseCode.WECHAT_REQUEST_ERROR);
    }

    @Override
    public ResponseData<Void> refund(WeChatRefundRequestVO refundRequestVO) {
        return ResponseData.fail(ResponseCode.WECHAT_REQUEST_ERROR);
    }
}
