package com.bosen.message.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 配置类
 * @author Lucas
 * @version 2.0.0
 * @date 2023/8/11
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig {
//     implements WebSocketConfigurer

//    @Resource
//    private WebSocketInterceptor webSocketInterceptor;
//
//    @Resource
//    private CustomWebSocketHandler customWebSocketHandler;

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        // 配置处理器
//        registry.addHandler(customWebSocketHandler, "/message/ws")
//                .addInterceptors(webSocketInterceptor)
//                // 解决跨域问题
//                .setAllowedOrigins("*");
//    }
}
