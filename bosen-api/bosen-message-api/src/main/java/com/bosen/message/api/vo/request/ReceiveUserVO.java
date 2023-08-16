package com.bosen.message.api.vo.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/8/15
 */
@Data
public class ReceiveUserVO implements Serializable {
    private static final long serialVersionUID = 2297675786980954890L;

    private String receiveUserId;

    private String receiveUserRoleId;
}
