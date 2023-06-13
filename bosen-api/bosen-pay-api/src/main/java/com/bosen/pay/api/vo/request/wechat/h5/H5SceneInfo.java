package com.bosen.pay.api.vo.request.wechat.h5;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * h5支付，场景信息
 * @author Lucas
 * @version 2.0.0
 * @date 2023/6/13
 */
@Data
public class H5SceneInfo implements Serializable {
    private static final long serialVersionUID = -8561974476821234815L;

    /**
     * 用户终端IP
     */
    @NotBlank(message = "用户终端IP不能为空")
    private String payer_client_ip;

    /**
     * h5场景信息
     */
    @NotNull(message = "h5场景信息不能为空")
    private H5Info h5_info;
}
