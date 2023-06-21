package com.bosen.pay.api.vo.request.alipay;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 支付宝支付基本参数
 * @author Lucas
 * @version 2.0.0
 * @date 2023/6/19
 */
@Data
public class AlipayRequestBase implements Serializable {
    private static final long serialVersionUID = -1787394908922646239L;

    /**
     * 支付宝分配给开发者的应用ID
     */
    @NotBlank(message = "应用ID不能为空")
    private String app_id;

    /**
     * HTTP/HTTPS开头字符串
     */
    private String return_url;

    private String charset = "utf-8";

    private String sign_type = "RSA2";

    /**
     * 支付宝服务器主动通知商户服务器里指定的页面http/https路径。
     */
    @NotBlank(message = "回调地址不能为空")
    private String notify_url;

    /**
     * 支付宝公钥
     */
    @NotBlank(message = "支付宝公钥不能为空")
    private String public_key;

    /**
     * 商户私钥
     */
    @NotBlank(message = "商户私钥不能为空")
    private String private_key;
}
