package com.bosen.pay.api.vo.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * native 支付基础参数
 * @author Lucas
 * @version 2.0.0
 * @date 2023/6/5
 */
@Data
public class NativePayRequestBase implements Serializable {

    private static final long serialVersionUID = -7608974353298177127L;
    /**
     * 应用ID
     */
    @NotBlank(message = "appid 不能为空")
    private String appid;

    /**
     * 直连商户号
     */
    @NotBlank(message = "商户号不能为空")
    private String mchid;

    /**
     * 商品描述
     */
    @NotBlank(message = "商品描述不能为空")
    private String description;

    /**
     * 商户订单号
     */
    @NotBlank(message = "商户订单号不能为空")
    private String out_trade_no;

    /**
     * 附加数据 附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用，实际情况下只有支付完成状态才会返回该字段
     */
    @NotBlank(message = "附加数据不能为空")
    private String attach;

    /**
     * 通知地址 通知URL必须为直接可访问的URL，不允许携带查询串，要求必须为https地址。
     */
    @NotBlank(message = "回调通知地址不能为空")
    private String notify_url;

    /**
     * 金额
     */
    @NotNull(message = "金额信息不能为空")
    private WeChatPayAmountVO amount;

}
