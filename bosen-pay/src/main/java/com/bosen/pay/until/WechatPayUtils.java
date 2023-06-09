package com.bosen.pay.until;

import com.bosen.pay.constant.WeChatPayConstant;
import com.bosen.pay.vo.response.KeyPairAndSerialNo;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/6/8
 */
public class WechatPayUtils {

    /**
     * 获取token
     * @param method 方法
     * @param body 请求参数
     * @param url 地址
     * @param mchId 商户号
     * @return 字符串数据
     */
    public static String getAuthorizationToken(String method, String body, String url, String mchId) {
        String nonceStr = getNonceStr();
        long timestamp = System.currentTimeMillis() / 1000;
        String signStr = buildSignStr(method, url, timestamp, nonceStr, body);
        KeyPairAndSerialNo keyPairAndSerialNo = KeyPairFactoryUntil.getKeyPair("/apiclient_cert.p12", WeChatPayConstant.KEY_ALIAS, "1431199002");
        String sign = WechatRSAUtils.sign(signStr, keyPairAndSerialNo.getKeyPair());
        return "mchid=\"" + mchId + "\","
                + "nonce_str=\"" + nonceStr + "\","
                + "timestamp=\"" + timestamp + "\","
                + "serial_no=\"" + keyPairAndSerialNo.getSerialNumber() + "\","
                + "signature=\"" + sign + "\"";
    }

    /**
     * 生成随机数
     * @return 随机数
     */
    public static String getNonceStr() {
        return UUID.randomUUID().toString()
                .replaceAll("-", "")
                .substring(0, 32);
    }

    /**
     * 构建加密参数
     * @return 需要加密的字符串
     */
    public static String buildSignStr(String method, String canonicalUrl, Long timeStamp, String nonceStr, String body) {
        return Stream.of(method,canonicalUrl, String.valueOf(timeStamp), nonceStr, body).collect(Collectors.joining("\n", "", "\n"));
    }
}

