//package com.bosen.message.interceptor;
//
//import com.bosen.common.constant.auth.AuthConstant;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.http.server.ServletServerHttpRequest;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
//
//import java.util.Map;
//
///**
// * websocket拦截器，用于处理是否登录
// * @author Lucas
// * @version 2.0.0
// * @date 2023/8/11
// */
//@Slf4j
//@Component
//public class WebSocketInterceptor extends HttpSessionHandshakeInterceptor {
//    @Override
//    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
//        log.info("我进来拦截器了==========");
//        // todo 接入登录
//        if (request instanceof ServletServerHttpRequest) {
//            ServletServerHttpRequest serverRequest = (ServletServerHttpRequest) request;
//            // 获取token标识
//            String token = serverRequest.getServletRequest().getParameter(AuthConstant.LOGIN_OUT_TOKEN_KEY);
//            if (!StringUtils.hasLength(token)) {
//                return false;
//            }
//            // 校验token
//            // 设置attributes
//        }
//        // 符合条件，继续执行逻辑
//        return super.beforeHandshake(request, response, wsHandler, attributes);
//    }
//}
