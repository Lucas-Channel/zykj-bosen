package com.bosen.message.api.vo.request;

import com.bosen.message.api.vo.response.WsMessageResponseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/8/15
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SendWsMessageBatchVO extends WsMessageResponseVO implements Serializable {

    private static final long serialVersionUID = 6662225335897066276L;
    /**
     * 接收用户集合
     */
    @NotEmpty(message = "接收用户集合不能为空")
    private List<ReceiveUserVO> receiveUser;
}
