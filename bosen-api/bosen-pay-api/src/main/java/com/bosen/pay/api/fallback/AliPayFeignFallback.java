package com.bosen.pay.api.fallback;

import com.bosen.common.constant.response.ResponseCode;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.pay.api.feign.IAliPayFeign;
import com.bosen.pay.api.vo.request.alipay.AlipayScanCodePayRequestVO;
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
    public ResponseData<String> scanCodeToPay(AlipayScanCodePayRequestVO payRequestVO) {
        return ResponseData.fail(ResponseCode.WECHAT_REQUEST_ERROR);
    }

    @Override
    public ResponseData<String> scanCodeFaceToFaceToPay(AlipayScanCodePayRequestVO payRequestVO) {
        return ResponseData.fail(ResponseCode.WECHAT_REQUEST_ERROR);
    }

    @Override
    public ResponseData<String> appToPay(AlipayScanCodePayRequestVO payRequestVO) {
        return ResponseData.fail(ResponseCode.WECHAT_REQUEST_ERROR);
    }

    @Override
    public ResponseData<String> mobileWebToPay(AlipayScanCodePayRequestVO payRequestVO) {
        return ResponseData.fail(ResponseCode.WECHAT_REQUEST_ERROR);
    }
}
