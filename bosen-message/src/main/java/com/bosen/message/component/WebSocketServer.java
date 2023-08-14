package com.bosen.message.component;

import com.bosen.common.constant.response.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/8/11
 */
@Slf4j
@Component
@ServerEndpoint("/message/ws/{userId}")
public class WebSocketServer {

    private static final ConcurrentHashMap<String, Session> sessionMap = new ConcurrentHashMap<>();

    /**
     * 连接开启调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        sessionMap.put(userId, session);
        log.info("有新连接加入：{}", session.getId());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session, @PathParam("userId") String userId) {
        sessionMap.remove(userId);
        log.info("有一连接关闭：{}", session.getId());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        if (Objects.equals(message, "unReadMsg")) {
            // 发送未读消息统计数据
        }
        log.info("服务端收到客户端[{}]的消息:{}", session.getId(), message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 服务端发送消息给客户端
     */
    private void sendMessage(String message, Session toSession) {
        try {
            log.info("服务端给客户端[{}]发送消息{}", toSession.getId(), message);
            toSession.getBasicRemote().sendText(message);
        } catch (Exception e) {
            log.error("服务端发送消息给客户端失败：{}", e.getMessage());
        }
    }

    /**
     * 服务端发送消息给客户端
     */
    public ResponseData<Void> sendMessage(String msg, String userId) {
       log.info("发消息给客户端");
        Session session = sessionMap.get(userId);
        if (Objects.nonNull(session)) {
            try {
                session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ResponseData.success();
    }
}
