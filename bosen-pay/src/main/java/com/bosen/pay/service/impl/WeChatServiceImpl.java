package com.bosen.pay.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.service.RedisService;
import com.bosen.pay.api.vo.request.NativePayRequest;
import com.bosen.pay.component.PayParamsConfig;
import com.bosen.pay.constant.PayInterfaceUrlConstant;
import com.bosen.pay.service.IWeChatService;
import com.bosen.pay.until.PayAesUtil;
import com.bosen.pay.until.WechatPayUtils;
import com.bosen.pay.until.WechatRSAUtils;
import com.bosen.pay.vo.response.WeChatCallbackVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 微信支付 接口逻辑处理
 * @author Lucas
 * @version 2.0.0
 * @date 2023/6/5
 */
@Slf4j
@Service
public class WeChatServiceImpl implements IWeChatService {

    @Resource
    private PayParamsConfig payParamsConfig;

    @Resource
    private RedisService redisService;

    @Resource
    private WechatPayUtils wechatPayUtils;

    /**
     * native支付，用于pc端支付，用于扫码支付
     * @param nativePayRequest 参数
     * @return 支付链接
     */
    @Override
    public ResponseData<String> nativePay(NativePayRequest nativePayRequest) {
        Map<String, Object> map = wechatPayUtils.doPost(payParamsConfig.getWxBaseUrl() + PayInterfaceUrlConstant.WE_CHAT_NATIVE_PAY, JSON.toJSONString(nativePayRequest.getBase()), nativePayRequest.getBase().getMchid(), nativePayRequest.getApiKey());
        return ResponseData.success(map.get("code_url").toString());
    }

    @Override
    public WeChatCallbackVO payCallBack(Map<String, Object> body, HttpServletRequest request) {
        log.info("接收到微信回调通知，接收参数为：{}", JSONObject.toJSON(body));
        WeChatCallbackVO callbackVO = new WeChatCallbackVO();
        try {
            String timestamp = request.getHeader("Wechatpay-Timestamp");
            String nonce = request.getHeader("Wechatpay-Nonce");
            // 微信回调应答签名
            String signBack = request.getHeader("Wechatpay-Signature");
            ObjectMapper objectMapper = new ObjectMapper();
            // 解析报文体
            String jsonData = objectMapper.writeValueAsString(body);
            String msg = timestamp + "\n" + nonce + "\n" + jsonData + "\n";
            // api证书序列号
            String serialNo = request.getHeader("Wechatpay-Serial");
            Object obj = redisService.get(serialNo);
            X509Certificate x509Certificate = (X509Certificate) obj;
            if (Objects.isNull(x509Certificate)) {
                callbackVO.setCode("FAIL");
                callbackVO.setMessage("未找到相关证书信息");
                return callbackVO;
            }
            if (!WechatRSAUtils.verify(msg, x509Certificate.getPublicKey(), signBack)) {
                callbackVO.setCode("FAIL");
                callbackVO.setMessage("微信支付回调验签失败");
                return callbackVO;
            }

            Map<String, String> resource = (Map)body.get("resource");
            Object priKey = redisService.get(serialNo + "_v3api_key");
            PayAesUtil payAesUtil = new PayAesUtil(priKey.toString().getBytes(StandardCharsets.UTF_8));
            // 解密
            String decryptToString = payAesUtil.decryptToString(resource.get("associated_data").getBytes(),
                    resource.get("nonce").getBytes(),
                    resource.get("ciphertext"));
            // 获取微信支付返回的信息
            Map<String, Object> result = JSON.parseObject(decryptToString, new TypeReference<HashMap<String, Object>>() {});
            // 支付状态的判断 如果是success就代表支付成功,根据订单号查询支付状态，如果未支付，更新支付状态 为已支付
            if ("SUCCESS".equalsIgnoreCase(result.get("trade_state").toString())) {
                // 获取支付的交易单号，流水号，和附属参数
                String out_trade_no = result.get("out_trade_no").toString();
                String transaction_id = result.get("transaction_id").toString();
                String attach = result.get("attach").toString();
                log.info("----------->微信支付成功,支付流水号是：{},附属参数是：{}, 商户订单号：{}", transaction_id, out_trade_no, attach);
                // todo 执行后续逻辑
                callbackVO.setCode("SUCCESS");
                callbackVO.setMessage("回调处理成功");
            } else {
                callbackVO.setCode("FAIL");
                callbackVO.setMessage("回调处理失败");
            }
        } catch (Exception e) {
            log.error("回调异常，错误信息如下：{}", e.getMessage());
            callbackVO.setCode("FAIL");
            callbackVO.setMessage("回调处理失败");
        }
        return callbackVO;
    }
}
