package com.bosen.pay.api.vo.request.wechat.jsapi;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * jsapi支付参数
 * @author Lucas
 * @version 2.0.0
 * @date 2023/6/13
 */
@Data
public class JsApiPayRequestVO implements Serializable {
    private static final long serialVersionUID = 8631467420855983827L;

    @NotNull(message = "基础信息不能为空")
    private JsApiPayRequestBaseVO base;

    /**
     * apiv3 key
     */
    @NotBlank(message = "apiKey不能为空")
    private String apiKey;
}
