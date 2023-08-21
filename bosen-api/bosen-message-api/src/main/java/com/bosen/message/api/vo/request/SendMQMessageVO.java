package com.bosen.message.api.vo.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/8/21
 */
@Data
public class SendMQMessageVO implements Serializable {
    private static final long serialVersionUID = 945882305703451537L;

    /**
     * exchange名称，SCS框架中的
     */
    @NotBlank(message = "exchange名称不能为空")
    private String bindingName;

    /**
     * 消息类型
     */
    @NotNull(message = "消息类型不能为空")
    private Integer msgType;

    /**
     * 消息
     */
    @NotBlank(message = "消息内容不能为空")
    private String msg;

    /**
     * 延迟时长
     */
    private Integer delaySeconds;
}
