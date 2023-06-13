package com.bosen.pay.until;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bosen.common.constant.response.ResponseCode;
import com.bosen.common.exception.BusinessException;
import com.bosen.common.service.RedisService;
import com.bosen.pay.component.PayParamsConfig;
import com.bosen.pay.constant.PayInterfaceUrlConstant;
import com.bosen.pay.constant.ReqMethodConstant;
import com.bosen.pay.constant.WeChatPayConstant;
import com.bosen.pay.vo.response.KeyPairAndSerialNo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/6/8
 */
@Component
public class WechatPayUtils {

    @Resource
    private KeyPairFactoryUntil keyPairFactoryUntil;

    @Resource
    private PayParamsConfig payParamsConfig;

    @Resource
    private RedisService redisService;
    /**
     * 获取token
     * @param method 方法
     * @param body 请求参数
     * @param url 地址
     * @param mchId 商户号
     * @return 字符串数据
     */
    public String getAuthorizationToken(String method, String body, String url, String mchId) {
        String nonceStr = getNonceStr();
        long timestamp = System.currentTimeMillis() / 1000;
        String signStr;
        try {
            signStr = buildSignStr(method, new URL(url), timestamp, nonceStr, body);
        } catch (MalformedURLException e) {
            throw new BusinessException(ResponseCode.TURN_URL_ERROR);
        }
        KeyPairAndSerialNo keyPairAndSerialNo = keyPairFactoryUntil.getKeyPair("/apiclient_cert.p12", WeChatPayConstant.KEY_ALIAS, mchId, mchId);
        String sign = WechatRSAUtils.sign(signStr, keyPairAndSerialNo.getKeyPair());
        return WeChatPayConstant.AUTH_TYPE + " mchid=\"" + mchId + "\","
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
    public static String buildSignStr(String method, URL url, Long timeStamp, String nonceStr, String body) {
        String canonicalUrl = url.getPath();
        if (url.getQuery() != null) {
            canonicalUrl += "?" + url.getQuery();
        }
        return Stream.of(method,canonicalUrl, String.valueOf(timeStamp), nonceStr, body).collect(Collectors.joining("\n", "", "\n"));
    }

    /**
     * post 请求
     * @param url 地址
     * @param body 请求参数
     * @param mchId 商户号
     * @return 支付参数
     */
    public Map<String, Object> doPost(String url, String body, String mchId, String apiKey) {
        if (!StringUtils.hasLength(body)) {
            throw new BusinessException(ResponseCode.REQUEST_BODY_IS_MISSING);
        }
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost  = new HttpPost(url);
        httpPost.addHeader("Content-Type","application/json;chartset=utf-8");
        httpPost.addHeader("Accept", "application/json");
        String token = getAuthorizationToken(ReqMethodConstant.POST, body, url, mchId);
        this.refreshCertificate(mchId, apiKey);
        httpPost.addHeader("Authorization", token);
        StringEntity stringEntity = new StringEntity(body, ContentType.APPLICATION_JSON);
        httpPost.setEntity(stringEntity);
        CloseableHttpResponse httpResponse;
        try {
            httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                String jsonResult = EntityUtils.toString(httpEntity);
                return JSON.parseObject(jsonResult, new TypeReference<HashMap<String, Object>>() {});
            } else {
                throw new BusinessException(ResponseCode.WECHAT_REQUEST_ERROR);
            }
        } catch (IOException e) {
            throw new BusinessException(ResponseCode.WECHAT_REQUEST_ERROR);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * get请求
     * @param url 地址
     * @return 结果
     */
    public JsonNode doGet(String url, String mchId){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpget = new HttpGet(url);
        httpget.addHeader("Accept", "application/json");
        httpget.addHeader("User-Agent", "https://zh.wikipedia.org/wiki/User_agent");
        try{
            String token = getAuthorizationToken("GET", "", url, mchId);
            httpget.addHeader("Authorization", token);
            CloseableHttpResponse httpResponse = httpClient.execute(httpget);
            if(httpResponse.getStatusLine().getStatusCode() == 200){
                String jsonResult = EntityUtils.toString( httpResponse.getEntity());
                return new ObjectMapper().readTree(jsonResult);
            }else{
                throw new BusinessException("请求失败");
            }

        }catch (Exception e){
            e.printStackTrace();
            throw new BusinessException("请求失败");
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void refreshCertificate(String mchId, String apiKey) {
        // 获取平台证书列表
        JsonNode jsonNode = this.doGet(payParamsConfig.getWxBaseUrl() + PayInterfaceUrlConstant.WE_CHAT_CERTIFICATES, mchId);
        // 获取平台验证的相关参数信息
        JsonNode data = jsonNode.get("data");
        if (Objects.nonNull(data)) {
            data.forEach(i -> {
                JsonNode encrypt_certificate = i.get("encrypt_certificate");
                // 对关键信息进行解密
                PayAesUtil aesUtil = new PayAesUtil(apiKey.getBytes(StandardCharsets.UTF_8));
                String associated_data = encrypt_certificate.get("associated_data").asText();
                String nonce = encrypt_certificate.get("nonce").asText();
                String ciphertext = encrypt_certificate.get("ciphertext").asText();
                // 证书内容
                String certStr;
                try {
                    certStr = aesUtil.decryptToString(associated_data.getBytes(), nonce.getBytes(), ciphertext);
                    // 证书内容转成证书对象
                    CertificateFactory cf = CertificateFactory.getInstance("X509");
                    X509Certificate x509Cert = (X509Certificate) cf.generateCertificate(
                            new ByteArrayInputStream(certStr.getBytes(StandardCharsets.UTF_8))
                    );
                    String serial_no = i.get("serial_no").asText();
                    redisService.set(serial_no, x509Cert);
                    redisService.set(serial_no + "_v3api_key", apiKey);
                } catch (GeneralSecurityException | IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}

