package com.bosen.pay.until;

import com.bosen.pay.vo.response.KeyPairAndSerialNo;
import org.springframework.core.io.ClassPathResource;

import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;

/**
 * 微信api证书解析工具
 * @author Lucas
 * @version 2.0.0
 * @date 2023/6/8
 */
public class KeyPairFactoryUntil {
    private static KeyStore store;

    private static final Object lock = new Object();

    /**
     * 获取api证书的公私钥
     * @param keyPath api证书路径
     * @param keyAlias 证书别名 固定为Tenpay Certificate
     * @param keyPass 证书密码 默认就是商户号
     * @return 公私钥
     */
    public static KeyPairAndSerialNo getKeyPair(String keyPath, String keyAlias, String keyPass) {
        KeyPairAndSerialNo data = new KeyPairAndSerialNo();
        // todo 替换成oss工具类
        ClassPathResource resource = new ClassPathResource(keyPath);
        char[] pem = keyPass.toCharArray();
        try {
            synchronized (lock) {
                if (store == null) {
                    synchronized (lock) {
                        store = KeyStore.getInstance("PKCS12");
                        store.load(resource.getInputStream(), pem);
                    }
                }
            }
            X509Certificate certificate = (X509Certificate) store.getCertificate(keyAlias);
            certificate.checkValidity();
            // 证书的序列号 也有用
             String serialNumber = certificate.getSerialNumber().toString(16).toUpperCase();
            // 证书的 公钥
            PublicKey publicKey = certificate.getPublicKey();
            // 证书的私钥
            PrivateKey storeKey = (PrivateKey) store.getKey(keyAlias, pem);

            return new KeyPairAndSerialNo(new KeyPair(publicKey, storeKey), serialNumber);
        } catch (Exception e) {
            throw new IllegalStateException("Cannot load keys from store: " + resource, e);
        }
    }
}
