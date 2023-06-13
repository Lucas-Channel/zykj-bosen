package com.bosen.pay.api.vo.request.wechat.nativepay;

import com.bosen.pay.api.vo.request.wechat.PayRequestBase;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * native 支付参数
 * @author Lucas
 * @version 2.0.0
 * @date 2023/6/5
 */
@Data
public class NativePayRequest implements Serializable {
    private static final long serialVersionUID = 1837758158692297461L;

    @NotNull(message = "基础参数不能为空")
    private PayRequestBase base;

    /**
     * apiv3 key
     */
    @NotBlank(message = "apiKey不能为空")
    private String apiKey;
}
