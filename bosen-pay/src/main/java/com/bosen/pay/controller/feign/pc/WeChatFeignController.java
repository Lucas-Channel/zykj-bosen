package com.bosen.pay.controller.feign.pc;

import com.bosen.common.constant.response.ResponseData;
import com.bosen.pay.api.vo.request.NativePayRequest;
import com.bosen.pay.service.IWeChatService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * pc支付接口
 * @author Lucas
 * @version 2.0.0
 * @date 2023/6/5
 */
@RestController
@RequestMapping("/pay/web/wechat")
public class WeChatFeignController {
    @Resource
    private IWeChatService weChatService;

    @PostMapping("/nativePay")
    public ResponseData<String> nativePay(@RequestBody @Valid NativePayRequest nativePayRequest) {
        return weChatService.nativePay(nativePayRequest);
    }
}
