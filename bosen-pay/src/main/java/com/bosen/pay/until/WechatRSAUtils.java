package com.bosen.pay.until;

import lombok.SneakyThrows;
import org.springframework.util.Base64Utils;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.Signature;

/**
 * 微信支付RSA签名工具类
 * @author Lucas
 * @version 2.0.0
 * @date 2023/6/8
 */
public class WechatRSAUtils {
    /**
     * 生成支付签名
     * @param signStr 签名内容
     * @param keyPair 公私钥
     * @return 加密后字符串
     **/
    @SneakyThrows
    public static String sign(String signStr, KeyPair keyPair) {
        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initSign(keyPair.getPrivate());
        sign.update(signStr.getBytes(StandardCharsets.UTF_8));
        return Base64Utils.encodeToString(sign.sign());
    }
}
