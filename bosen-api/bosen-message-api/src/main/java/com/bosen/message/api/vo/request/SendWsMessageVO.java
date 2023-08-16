package com.bosen.message.api.vo.request;

import com.bosen.message.api.vo.response.WsMessageResponseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/8/15
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SendWsMessageVO extends WsMessageResponseVO implements Serializable {
    private static final long serialVersionUID = -7054729743402798872L;

    /**
     * 接收用户id
     */
    @NotEmpty(message = "接收用户id不能为空")
    private String receiveUserId;

    @NotEmpty(message = "接收用户角色id不能为空")
    private String receiveUserRoleId;

}
