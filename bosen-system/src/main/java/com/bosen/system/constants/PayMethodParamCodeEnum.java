package com.bosen.system.constants;

/**
 * 支付参数配置
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/6
 */
public enum PayMethodParamCodeEnum {
    /**
     * 支付宝 - appId
     */
    ALIPAY_APP_ID(1, "app_id"),

    /**
     * 支付宝 - 公钥
     */
    ALIPAY_PUBLIC_KEY(2, "rsa_public_key"),

    /**
     * 支付宝 - 私钥
     */
    APP_ALIPAY_PRIVATE_KEY(3, "app_rsa_private_key"),
    /**
     * 微信 - 商户号
     */
    WECHAT_MERCHANT_ID(4, "MchId"),
    /**
     * 微信 - appid
     */
    WECHAT_APP_ID(1, "AppId"),
    /**
     * 微信 - app密钥
     */
    WECHAT_API_KEY(1, "AppKey"),
    ;
    private final Integer sort;
    private final String paramCode;

    PayMethodParamCodeEnum(Integer sort, String paramCode) {
        this.sort = sort;
        this.paramCode = paramCode;
    }

    public Integer getSort() {
        return sort;
    }

    public String getParamCode() {
        return paramCode;
    }
}
