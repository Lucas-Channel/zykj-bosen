package com.bosen.pay.api.vo.request.wechat.jsapi;

import com.bosen.pay.api.vo.request.wechat.PayRequestBaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/6/13
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class JsApiPayRequestBaseVO extends PayRequestBaseVO implements Serializable {
    private static final long serialVersionUID = -3399439115033742426L;

    /**
     * 支付者
     */
    private PayerVO payer;
}
