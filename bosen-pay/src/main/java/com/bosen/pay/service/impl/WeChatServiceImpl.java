package com.bosen.pay.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.bosen.common.constant.response.ResponseCode;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.exception.BusinessException;
import com.bosen.common.service.RedisService;
import com.bosen.pay.api.constant.WeChatPayStatusConstant;
import com.bosen.pay.api.constant.WeChatRefundStatusConstant;
import com.bosen.pay.api.vo.request.wechat.WeChatRefundRequestVO;
import com.bosen.pay.api.vo.request.wechat.h5.H5PayRequestVO;
import com.bosen.pay.api.vo.request.wechat.jsapi.JsApiPayRequestVO;
import com.bosen.pay.api.vo.request.wechat.nativepay.NativePayRequestVO;
import com.bosen.pay.component.PayParamsConfig;
import com.bosen.pay.constant.PayInterfaceUrlConstant;
import com.bosen.pay.service.IWeChatService;
import com.bosen.pay.until.PayAesUtil;
import com.bosen.pay.until.WechatPayUtils;
import com.bosen.pay.until.WechatRSAUtils;
import com.bosen.pay.vo.response.WeChatCallbackVO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
     * @param nativePayRequestVO 参数
     * @return 支付链接
     */
    @Override
    public ResponseData<String> nativePay(NativePayRequestVO nativePayRequestVO) {
        checkUrl();
        Map<String, Object> map = wechatPayUtils.doPost(payParamsConfig.getWxBaseUrl() + PayInterfaceUrlConstant.WE_CHAT_NATIVE_PAY, JSON.toJSONString(nativePayRequestVO.getBase()), nativePayRequestVO.getBase().getMchid(), nativePayRequestVO.getApiKey());
        return ResponseData.success(map.get("code_url").toString());
    }

    @Override
    public ResponseData<String> h5PayRequest(H5PayRequestVO h5PayRequestVO) {
        checkUrl();
        Map<String, Object> map = wechatPayUtils.doPost(payParamsConfig.getWxBaseUrl() + PayInterfaceUrlConstant.WE_CHAT_H5_PAY, JSON.toJSONString(h5PayRequestVO.getBase()), h5PayRequestVO.getBase().getMchid(), h5PayRequestVO.getApiKey());
        return ResponseData.success(map.get("h5_url").toString());
    }

    @Override
    public ResponseData<String> jsApiPayRequest(JsApiPayRequestVO jsApiPayRequestVO) {
        checkUrl();
        Map<String, Object> map = wechatPayUtils.doPost(payParamsConfig.getWxBaseUrl() + PayInterfaceUrlConstant.WE_CHAT_JSAPI_PAY, JSON.toJSONString(jsApiPayRequestVO.getBase()), jsApiPayRequestVO.getBase().getMchid(), jsApiPayRequestVO.getApiKey());
        return ResponseData.success(map.get("prepay_id").toString());
    }

    @Override
    public WeChatCallbackVO payCallBack(Map<String, Object> body, HttpServletRequest request) {
        log.info("接收到微信回调通知，接收参数为：{}", JSONObject.toJSON(body));
        WeChatCallbackVO callbackVO = new WeChatCallbackVO();
        try {
            Map<String, Object> result = callBackBodyVerify(body, request);
            // 支付状态的判断 如果是success就代表支付成功,根据订单号查询支付状态，如果未支付，更新支付状态 为已支付
            if (WeChatPayStatusConstant.SUCCESS.equalsIgnoreCase(result.get("trade_state").toString())) {
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

    @Override
    public WeChatCallbackVO refundNotify(Map<String, Object> body, HttpServletRequest request) {
        log.info("接收到微信退款回调通知，接收参数为：{}", JSONObject.toJSON(body));
        WeChatCallbackVO callbackVO = new WeChatCallbackVO();
        try {
            Map<String, Object> result = callBackBodyVerify(body, request);
            // 支付状态的判断 如果是success就代表成功
            if (WeChatPayStatusConstant.SUCCESS.equalsIgnoreCase(result.get("refund_status").toString())) {
                // 获取支付的交易单号，流水号，和附属参数
                String out_trade_no = result.get("out_trade_no").toString();
                String transaction_id = result.get("transaction_id").toString();
                String out_refund_no = result.get("out_refund_no").toString();
                String refund_id = result.get("refund_id").toString();
                log.info("----------->微信退款成功,支付流水号是：{},附属参数是：{}, 商户退款单号：{}, 微信支付退款单号:{}", transaction_id, out_trade_no, out_refund_no, refund_id);
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

    @SneakyThrows
    public Map<String, Object> callBackBodyVerify(Map<String, Object> body, HttpServletRequest request) {
        String timestamp = request.getHeader("Wechatpay-Timestamp");
        String nonce = request.getHeader("Wechatpay-Nonce");
        ObjectMapper objectMapper = new ObjectMapper();
        // 解析报文体
        String jsonData = objectMapper.writeValueAsString(body);
        // 微信回调应答签名
        String signBack = request.getHeader("Wechatpay-Signature");
        // api证书序列号
        String serialNo = request.getHeader("Wechatpay-Serial");
        String msg = timestamp + "\n" + nonce + "\n" + jsonData + "\n";
        Object obj = redisService.get(serialNo);
        X509Certificate x509Certificate = (X509Certificate) obj;
        if (Objects.isNull(x509Certificate)) {
            throw new BusinessException("未找到相关证书信息");
        }
        if (!WechatRSAUtils.verify(msg, x509Certificate.getPublicKey(), signBack)) {
            throw new BusinessException("微信回调验签失败");
        }

        Map<String, String> resource = (Map)body.get("resource");
        Object priKey = redisService.get(serialNo + "_v3api_key");
        PayAesUtil payAesUtil = new PayAesUtil(priKey.toString().getBytes(StandardCharsets.UTF_8));
        // 解密
        String decryptToString = payAesUtil.decryptToString(resource.get("associated_data").getBytes(),
                resource.get("nonce").getBytes(),
                resource.get("ciphertext"));
        log.info("微信回调参数：{}", decryptToString);
        // 获取微信回调返回的信息
        return JSON.parseObject(decryptToString, new TypeReference<HashMap<String, Object>>() {});
    }

    @Override
    public ResponseData<Boolean> queryOrderPayStatus(String mchId, String outTradeNo) {
        checkUrl();
        String doUrl = payParamsConfig.getWxBaseUrl() + String.format(PayInterfaceUrlConstant.WE_CHAT_QUERY_ORDER_STATUS, outTradeNo) + "?mchid=" + mchId;
        JsonNode data = wechatPayUtils.doGet(doUrl, mchId);
        boolean status = false;
        if (Objects.nonNull(data)) {
            // 详细内容见官网 https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_4_5.shtml
            String trade_state = data.get("trade_state").asText();
            if (WeChatPayStatusConstant.SUCCESS.equalsIgnoreCase(trade_state)) {
                status = true;
            }
        }
        return ResponseData.success(status);
    }

    @Override
    public ResponseData<Void> refund(WeChatRefundRequestVO refundRequestVO) {
        checkUrl();
        String doUrl = payParamsConfig.getWxBaseUrl() + PayInterfaceUrlConstant.WE_CHAT_REFUNDS;
        Map<String, Object> map = wechatPayUtils.doPost(doUrl, JSON.toJSONString(refundRequestVO.getBase()), refundRequestVO.getMchId(), refundRequestVO.getApiKey());
        if (WeChatRefundStatusConstant.SUCCESS.equalsIgnoreCase(map.get("status").toString()) || WeChatRefundStatusConstant.PROCESSING.equalsIgnoreCase(map.get("status").toString())) {
            return ResponseData.success();
        }
        return ResponseData.fail();
    }

    private void checkUrl() {
        if (!StringUtils.hasLength(payParamsConfig.getWxBaseUrl()) || null == payParamsConfig.getWxBaseUrl()) {
            throw new BusinessException(ResponseCode.THIRD_URL_NOT_EXIT_ERROR);
        }
    }
}
