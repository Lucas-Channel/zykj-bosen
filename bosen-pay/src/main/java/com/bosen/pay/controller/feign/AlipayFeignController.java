package com.bosen.pay.controller.feign;

import com.bosen.common.constant.response.ResponseData;
import com.bosen.pay.api.vo.request.alipay.AlipayScanCodePayRequestVO;
import com.bosen.pay.service.IAlipayService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 支付宝支付
 * @author Lucas
 * @version 2.0.0
 * @date 2023/6/19
 */
@RestController
@RequestMapping("/pay/alipay")
public class AlipayFeignController {

    @Resource
    private IAlipayService alipayService;

    /**
     * 扫码支付，电脑支付
     * @return form表单
     */
    @PostMapping("/scanCodeToPay")
    public ResponseData<String> scanCodeToPay(@RequestBody @Valid AlipayScanCodePayRequestVO payRequestVO) {
        return alipayService.scanCodeToPay(payRequestVO);
    }

    /**
     * 扫码支付，面对面扫码支付
     * @return 二维码链接内容
     */
    @PostMapping("/scanCodeFaceToFaceToPay")
    public ResponseData<String> scanCodeFaceToFaceToPay(@RequestBody @Valid AlipayScanCodePayRequestVO payRequestVO) {
        return alipayService.scanCodeFaceToFaceToPay(payRequestVO);
    }


    /**
     * app支付
     * @return form表单
     */
    @PostMapping("/appToPay")
    public ResponseData<String> appToPay(@RequestBody @Valid AlipayScanCodePayRequestVO payRequestVO) {
        return alipayService.appToPay(payRequestVO);
    }

    /**
     * mobile web支付
     * @return form表单
     */
    @PostMapping("/mobileWebToPay")
    public ResponseData<String> mobileWebToPay(@RequestBody @Valid AlipayScanCodePayRequestVO payRequestVO) {
        return alipayService.mobileWebToPay(payRequestVO);
    }
}
