package com.bosen.pay.service.impl;

import com.alibaba.fastjson.JSON;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.pay.api.vo.request.NativePayRequest;
import com.bosen.pay.constant.ReqMethodConstant;
import com.bosen.pay.service.IWeChatService;
import com.bosen.pay.until.WechatPayUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 微信支付 接口逻辑处理
 * @author Lucas
 * @version 2.0.0
 * @date 2023/6/5
 */
@Slf4j
@Service
public class WeChatServiceImpl implements IWeChatService {

    /**
     * native支付，用于pc端支付，用于扫码支付
     * @param nativePayRequest 参数
     * @return 支付链接
     */
    @Override
    public ResponseData<String> nativePay(NativePayRequest nativePayRequest) {
        String token = WechatPayUtils.getAuthorizationToken(ReqMethodConstant.POST, JSON.toJSONString(nativePayRequest), "v3/certificates", "1431199002");
        return ResponseData.success(token);
    }
}
