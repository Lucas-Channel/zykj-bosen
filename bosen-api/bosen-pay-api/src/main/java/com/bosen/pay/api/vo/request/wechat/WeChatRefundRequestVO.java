package com.bosen.pay.api.vo.request.wechat;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 退款请求参数
 * @author Lucas
 * @version 2.0.0
 * @date 2023/6/13
 */
@Data
public class WeChatRefundRequestVO implements Serializable {
    private static final long serialVersionUID = -7607072854318524485L;
    @NotNull(message = "基础参数不能为空")
    private WeChatRefundRequestBaseVO base;

    /**
     * apiv3 key
     */
    @NotBlank(message = "apiKey不能为空")
    private String apiKey;

    @NotBlank(message = "商户号不能为空")
    private String mchId;
}
