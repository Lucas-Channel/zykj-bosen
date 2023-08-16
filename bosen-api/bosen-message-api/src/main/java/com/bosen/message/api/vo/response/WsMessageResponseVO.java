package com.bosen.message.api.vo.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/8/15
 */
@Data
public class WsMessageResponseVO implements Serializable {
    private static final long serialVersionUID = 8043770197147484244L;

    private String title;

    private String content;

    private String url;

    /**
     * 未读数量
     */
    private Integer unReadMsgCount;
}
