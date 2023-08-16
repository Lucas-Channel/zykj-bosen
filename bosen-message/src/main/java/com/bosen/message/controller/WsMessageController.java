package com.bosen.message.controller;

import com.bosen.common.constant.response.ResponseData;
import com.bosen.message.api.vo.request.SendWsMessageBatchVO;
import com.bosen.message.api.vo.request.SendWsMessageVO;
import com.bosen.message.component.WebSocketServer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/8/15
 */
@RestController
@RequestMapping("/ws/message")
public class WsMessageController {

    @Resource
    private WebSocketServer webSocketServer;

    @PostMapping("/send")
    public ResponseData<Void> send(@RequestBody @Valid SendWsMessageVO wsMessageVO) {
        return webSocketServer.sendMessage(wsMessageVO);
    }

    @PostMapping("/sendBatch")
    public ResponseData<Void> sendBatch(@RequestBody @Valid SendWsMessageBatchVO wsMessageVO) {
        return webSocketServer.sendMessageBatch(wsMessageVO);
    }
}
