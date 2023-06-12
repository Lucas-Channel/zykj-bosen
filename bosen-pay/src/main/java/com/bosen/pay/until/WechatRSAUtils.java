package com.bosen.pay.until;

import lombok.SneakyThrows;
import org.springframework.util.Base64Utils;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

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

    /**
     * 用于回调验签
     * @param signStr 自己拼接的参数
     * @param publicKey 公私钥
     * @param signWeChat 回调内容
     * @return true or false
     */
    public static boolean verify(String signStr, PublicKey publicKey, String signWeChat) {
        try {
            Signature sign = Signature.getInstance("SHA256withRSA");
            sign.initVerify(publicKey);
            sign.update(signStr.getBytes(StandardCharsets.UTF_8));
            return sign.verify(Base64.getDecoder().decode(signWeChat));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("当前Java环境不支持SHA256withRSA", e);
        } catch (SignatureException e) {
            throw new RuntimeException("签名验证过程发生了错误", e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException("无效的证书", e);
        }
    }
}
