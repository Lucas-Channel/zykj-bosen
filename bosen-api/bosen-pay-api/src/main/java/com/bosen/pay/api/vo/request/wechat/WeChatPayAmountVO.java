package com.bosen.pay.api.vo.request.wechat;

import lombok.Data;

import java.io.Serializable;

/**
 * 支付金额
 * @author Lucas
 * @version 2.0.0
 * @date 2023/6/6
 */
@Data
public class WeChatPayAmountVO implements Serializable {
    private static final long serialVersionUID = 6076754666735758723L;

    /**
     * 单位为分
     */
    private Integer total;

    /**
     * 币种：CNY
     */
    private String currency = "CNY";
}
