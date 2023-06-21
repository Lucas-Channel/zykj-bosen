package com.bosen.pay.api.feign;

import com.bosen.common.constant.response.ResponseData;
import com.bosen.pay.api.fallback.AliPayFeignFallback;
import com.bosen.pay.api.vo.request.wechat.WeChatRefundRequestVO;
import com.bosen.pay.api.vo.request.wechat.h5.H5PayRequestVO;
import com.bosen.pay.api.vo.request.wechat.jsapi.JsApiPayRequestVO;
import com.bosen.pay.api.vo.request.wechat.nativepay.NativePayRequestVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * 微信支付-内部调用接口
 * @author Lucas
 * @version 2.0.0
 * @date 2023/6/21
 */
@FeignClient(value = "bosen-pay", fallback = AliPayFeignFallback.class)
public interface IWechatPayFeign {

    /**
     * native支付，用于pc端支付，用于扫码支付
     * @param nativePayRequestVO 参数
     * @return 支付链接
     */
    @PostMapping("/nativePay")
    ResponseData<String> nativePay(@RequestBody @Valid NativePayRequestVO nativePayRequestVO);

    /**
     * h5支付
     * @param h5PayRequestVO 参数
     * @return 支付链接
     */
    @PostMapping("/h5Pay")
    ResponseData<String> h5PayRequest(@RequestBody @Valid H5PayRequestVO h5PayRequestVO);

    /**
     * jsapi支付
     * @param jsApiPayRequestVO 参数
     * @return 支付链接
     */
    @PostMapping("/jsApiPay")
    ResponseData<String> jsApiPayRequest(@RequestBody @Valid JsApiPayRequestVO jsApiPayRequestVO);

    /**
     * 主动查询订单支付结果
     * @param mchId 商户号
     * @param outTradeNo 交易单号
     * @return true支付成功，false支付失败
     */
    @GetMapping("/queryOrderPayStatus")
    ResponseData<Boolean> queryOrderPayStatus(String mchId, String outTradeNo);

    /**
     * 退款申请
     * @param refundRequestVO 参数
     * @return 结果
     */
    @PostMapping("/refund")
    ResponseData<Void> refund(@RequestBody @Valid WeChatRefundRequestVO refundRequestVO);
}
