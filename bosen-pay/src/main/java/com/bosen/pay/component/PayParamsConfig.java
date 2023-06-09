package com.bosen.pay.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 支付参数相关
 * @author Lucas
 * @version 2.0.0
 * @date 2023/6/7
 */
@Data
@Component
@ConfigurationProperties(prefix = "pay")
public class PayParamsConfig {
    /**
     * 微信基础地址
     */
    private String wxBaseUrl;


}
