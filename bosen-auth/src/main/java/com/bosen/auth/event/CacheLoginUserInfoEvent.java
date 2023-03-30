package com.bosen.auth.event;

import org.springframework.context.ApplicationEvent;

/**
 * 缓存登录信息事件定义类
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/23
 */
public class CacheLoginUserInfoEvent extends ApplicationEvent {
    private String id;

    private String clientId;

    public CacheLoginUserInfoEvent(Object source, String id, String clientId) {
        super(source);
        this.id = id;
        this.clientId = clientId;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
