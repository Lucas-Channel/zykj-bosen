package com.bosen.pay.api.vo.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 退款金额信息
 * @author Lucas
 * @version 2.0.0
 * @date 2023/6/13
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WeChatRefundAmountRequestVO extends WeChatPayAmountVO implements Serializable {
    private static final long serialVersionUID = 2691537738697779920L;

    /**
     * 退款金额
     */
    private Integer refund;
}
