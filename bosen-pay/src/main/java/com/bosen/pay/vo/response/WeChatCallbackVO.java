package com.bosen.pay.vo.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 微信回调通知结果返回对象
 * @author Lucas
 * @version 2.0.0
 * @date 2023/6/9
 */
@Data
public class WeChatCallbackVO implements Serializable {
    private static final long serialVersionUID = -5488484015763518423L;

    /**
     * 错误码，SUCCESS为清算机构接收成功，其他错误码为失败。
     */
    private String code;

    private String message;
}
