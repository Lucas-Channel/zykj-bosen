package com.bosen.pay.api.fallback;

import com.bosen.common.constant.response.ResponseCode;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.pay.api.feign.IAliPayFeign;
import com.bosen.pay.api.vo.request.alipay.AlipayRequestVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/25
 */
@Slf4j
@Component
public class AliPayFeignFallback implements IAliPayFeign {

    @Override
    public ResponseData<String> scanCodeToPay(AlipayRequestVO payRequestVO) {
        return ResponseData.fail(ResponseCode.WECHAT_REQUEST_ERROR);
    }

    @Override
    public ResponseData<String> scanCodeFaceToFaceToPay(AlipayRequestVO payRequestVO) {
        return ResponseData.fail(ResponseCode.WECHAT_REQUEST_ERROR);
    }

    @Override
    public ResponseData<String> appToPay(AlipayRequestVO payRequestVO) {
        return ResponseData.fail(ResponseCode.WECHAT_REQUEST_ERROR);
    }

    @Override
    public ResponseData<String> mobileWebToPay(AlipayRequestVO payRequestVO) {
        return ResponseData.fail(ResponseCode.WECHAT_REQUEST_ERROR);
    }
}
