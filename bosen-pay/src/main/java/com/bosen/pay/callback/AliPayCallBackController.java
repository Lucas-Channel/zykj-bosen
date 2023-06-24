package com.bosen.pay.callback;

import com.bosen.pay.service.IAlipayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/6/9
 */
@Slf4j
@RestController
@RequestMapping("/pay/alipay")
public class AliPayCallBackController {

    @Resource
    private IAlipayService alipayService;

    /**
     * 支付宝回调通知
     * @param body 参数
     * @param request httpRequest
     * @return 回调处理结果: SUCCESS OR FAILURE
     */
    @PostMapping("/aliPayNotify")
    public String aliPayNotify(@RequestBody Map<String, Object> body, HttpServletRequest request) {
        return alipayService.payCallBack(body, request);
    }

}
