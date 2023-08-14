package com.bosen.message;

import com.bosen.common.constant.response.ResponseData;
import com.bosen.message.component.WebSocketServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/8/11
 */
@RestController
@RequestMapping("/msg/ws")
public class Test {

    @Resource
    private WebSocketServer webSocketServer;

    @GetMapping
    public ResponseData<Void> send() {
       return webSocketServer.sendMessage("给客户端发消息", "2002");
    }
}
