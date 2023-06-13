package com.bosen.pay.api.vo.request.wechat.h5;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * h5支付
 * @author Lucas
 * @version 2.0.0
 * @date 2023/6/13
 */
@Data
public class H5PayRequestVO implements Serializable {
    private static final long serialVersionUID = -4048265855954681127L;

    @NotNull(message = "基础信息不能为空")
    private H5PayRequestBaseVO base;

    /**
     * apiv3 key
     */
    @NotBlank(message = "apiKey不能为空")
    private String apiKey;
}

