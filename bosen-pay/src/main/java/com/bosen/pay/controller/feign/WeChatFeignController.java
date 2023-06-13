package com.bosen.pay.controller.feign;

import com.bosen.common.constant.response.ResponseData;
import com.bosen.pay.api.vo.request.NativePayRequest;
import com.bosen.pay.api.vo.request.WeChatRefundRequestVO;
import com.bosen.pay.service.IWeChatService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * pc支付接口
 * @author Lucas
 * @version 2.0.0
 * @date 2023/6/5
 */
@RestController
@RequestMapping("/pay/wechat")
public class WeChatFeignController {
    @Resource
    private IWeChatService weChatService;

    /**
     * native支付，用于pc端支付，用于扫码支付
     * @param nativePayRequest 参数
     * @return 支付链接
     */
    @PostMapping("/nativePay")
    public ResponseData<String> nativePay(@RequestBody @Valid NativePayRequest nativePayRequest) {
        return weChatService.nativePay(nativePayRequest);
    }

    /**
     * 主动查询订单支付结果
     * @param mchId 商户号
     * @param outTradeNo 交易单号
     * @return true支付成功，false支付失败
     */
    @GetMapping("/queryOrderPayStatus")
    public ResponseData<Boolean> queryOrderPayStatus(String mchId, String outTradeNo) {
        return weChatService.queryOrderPayStatus(mchId, outTradeNo);
    }

    /**
     * 退款申请
     * @param refundRequestVO 参数
     * @return 结果
     */
    @PostMapping("/refund")
    public ResponseData<Void> refund(@RequestBody @Valid WeChatRefundRequestVO refundRequestVO) {
        return weChatService.refund(refundRequestVO);
    }
}