package com.bosen.pay.api.vo.request.wechat.jsapi;

import lombok.Data;

import java.io.Serializable;

/**
 * 支付者
 * @author Lucas
 * @version 2.0.0
 * @date 2023/6/13
 */
@Data
public class PayerVO implements Serializable {
    private static final long serialVersionUID = 7960511257770724757L;

    private String openid;
}
