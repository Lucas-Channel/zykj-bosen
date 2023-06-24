package com.bosen.pay.api.feign;

import com.bosen.common.constant.response.ResponseData;
import com.bosen.pay.api.fallback.AliPayFeignFallback;
import com.bosen.pay.api.vo.request.alipay.AlipayRequestVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * 微信支付-内部调用接口
 * contextId,高版本的spring boot 对于FeignClient，如果value一样，必须定义contextId
 * @author Lucas
 * @version 2.0.0
 * @date 2023/6/21
 */
@FeignClient(value = "bosen-pay", fallback = AliPayFeignFallback.class, contextId = "aliPayFeign")
public interface IAliPayFeign {
    /**
     * 扫码支付，电脑支付
     * @return form表单
     */
    @PostMapping("/pay/alipay/scanCodeToPay")
    ResponseData<String> scanCodeToPay(@RequestBody @Valid AlipayRequestVO payRequestVO);

    /**
     * 扫码支付，面对面扫码支付
     * @return 二维码链接内容
     */
    @PostMapping("/pay/alipay/scanCodeFaceToFaceToPay")
    ResponseData<String> scanCodeFaceToFaceToPay(@RequestBody @Valid AlipayRequestVO payRequestVO);


    /**
     * app支付
     * @return form表单
     */
    @PostMapping("/pay/alipay/appToPay")
    ResponseData<String> appToPay(@RequestBody @Valid AlipayRequestVO payRequestVO);

    /**
     * mobile web支付
     * @return form表单
     */
    @PostMapping("/pay/alipay/mobileWebToPay")
    ResponseData<String> mobileWebToPay(@RequestBody @Valid AlipayRequestVO payRequestVO);
}
