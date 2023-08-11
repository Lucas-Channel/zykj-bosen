//package com.bosen.message.handler;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
///**
// * @author Lucas
// * @version 2.0.0
// * @date 2023/8/11
// */
//@Slf4j
//@Component
//public class CustomWebSocketHandler extends TextWebSocketHandler {
//    // 使用session获取从拦截器设置的参数，链接成功之后，设置session缓存
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        System.out.println("============================进来处理器了0");
//        super.afterConnectionEstablished(session);
//    }
//
//    @Override
//    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
//        System.out.println("============================进来处理器了1");
//        super.handleMessage(session, message);
//    }
//
//    @Override
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        System.out.println("============================进来处理器了2");
//        super.handleTextMessage(session, message);
//    }
//
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        System.out.println("============================进来处理器了3");
//        super.afterConnectionClosed(session, status);
//    }
//
//    @Override
//    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
//        System.out.println("============================进来处理器了4");
//        super.handleTransportError(session, exception);
//    }
//}
