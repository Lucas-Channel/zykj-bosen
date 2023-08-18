package com.bosen.message.component;

import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.util.JacksonUtils;
import com.bosen.common.util.SpringBeanUtils;
import com.bosen.message.api.vo.request.SendWsMessageBatchVO;
import com.bosen.message.api.vo.request.SendWsMessageVO;
import com.bosen.message.api.vo.response.WsMessageResponseVO;
import com.bosen.message.service.IBsMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 1、由于spring bean 是在项目启动注入的，在注入到spring容器后是静态的，无法在修改，
 * 而websocket是在运行时进行双向通信，一定程度上ws的注入方式和spring是不一样的，他的创建是可能发生在运行时，所以需要通过上下文获取bean
 * @author Lucas
 * @version 2.0.0
 * @date 2023/8/11
 */
@Slf4j
@Component
@ServerEndpoint("/message/ws/{userId}/{roleId}")
public class WebSocketServer {

    private static final ConcurrentHashMap<String, Session> sessionMap = new ConcurrentHashMap<>();

    /**
     * 连接开启调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId, @PathParam("roleId") String roleId) {
        sessionMap.put(userId + "_" + roleId, session);
        log.info("有新连接加入：{}", session.getId());
        // 推送未读消息总数量
        IBsMessageService messageService = SpringBeanUtils.getBean(IBsMessageService.class);
        ResponseData<Integer> unreadCount = messageService.getUnreadCount(userId, roleId);
        WsMessageResponseVO response = new WsMessageResponseVO();
        response.setUnReadMsgCount(unreadCount.getData());
        response.setTitle("未读统计");
        response.setContent("您有" + response.getUnReadMsgCount() + "条未读消息，请及时处理！");
        this.sendMessage(response, userId, roleId);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session, @PathParam("userId") String userId, @PathParam("roleId") String roleId) {
        sessionMap.remove(userId + "_" + roleId);
        log.info("有一连接关闭：{}", session.getId());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
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
    public ResponseData<Void> sendMessage(SendWsMessageVO wsMessageVO) {
       log.info("发消息给客户端");
        IBsMessageService messageService = SpringBeanUtils.getBean(IBsMessageService.class);
        Session session = sessionMap.get(wsMessageVO.getReceiveUserId() + "_" + wsMessageVO.getReceiveUserRoleId());
        if (Objects.nonNull(session)) {
            try {
                WsMessageResponseVO response = new WsMessageResponseVO();
                response.setTitle(wsMessageVO.getTitle());
                response.setContent(wsMessageVO.getContent());
                ResponseData<Integer> unreadCount = messageService.getUnreadCount(wsMessageVO.getReceiveUserId(), wsMessageVO.getReceiveUserRoleId());
                response.setUnReadMsgCount(unreadCount.getData());
                session.getBasicRemote().sendText(JacksonUtils.toJson(wsMessageVO));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ResponseData.success();
    }

    /**
     * 服务端发送消息给客户端
     */
    public ResponseData<Void> sendMessage(WsMessageResponseVO msg, String userId, String roleId) {
        log.info("发消息给客户端");
        Session session = sessionMap.get(userId + "_" + roleId);
        if (Objects.nonNull(session)) {
            try {
                session.getBasicRemote().sendText(JacksonUtils.toJson(msg));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ResponseData.success();
    }

    /**
     * 服务端发送消息给客户端
     */
    public ResponseData<Void> sendMessageBatch(SendWsMessageBatchVO wsMessageVO) {
        log.info("发消息给客户端");
        WsMessageResponseVO response = new WsMessageResponseVO();
        response.setTitle(wsMessageVO.getTitle());
        response.setContent(wsMessageVO.getContent());
        IBsMessageService messageService = SpringBeanUtils.getBean(IBsMessageService.class);
        wsMessageVO.getReceiveUser().forEach(i -> {
            ResponseData<Integer> unreadCount = messageService.getUnreadCount(i.getReceiveUserId(), i.getReceiveUserRoleId());
            response.setUnReadMsgCount(unreadCount.getData());
            this.sendMessage(response, i.getReceiveUserId(), i.getReceiveUserRoleId());
        });
        return ResponseData.success();
    }
}
