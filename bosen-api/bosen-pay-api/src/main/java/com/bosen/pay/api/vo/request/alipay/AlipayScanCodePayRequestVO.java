package com.bosen.pay.api.vo.request.alipay;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/6/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AlipayScanCodePayRequestVO extends AlipayRequestBase implements Serializable {
    private static final long serialVersionUID = -4566430059169609879L;

    /**
     * 商户订单号。
     */
    @NotBlank(message = "商户订单号不能为空")
    private String out_trade_no;

    /**
     * 订单总金额。
     * 单位为元，精确到小数点后两位
     */
    @NotNull(message = "订单总金额不能为空")
    private BigDecimal total_amount;

    /**
     * 订单标题。注意：不可使用特殊字符，如 /，=，& 等。
     */
    @NotBlank(message = "订单标题不能为空")
    private String subject;

    /**
     * 订单附加信息。
     */
    private String body;

}
