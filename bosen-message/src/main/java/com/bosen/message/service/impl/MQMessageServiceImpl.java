package com.bosen.message.service.impl;

import com.bosen.common.constant.response.ResponseData;
import com.bosen.message.service.IMQMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/8/18
 */
@Slf4j
@Service
public class MQMessageServiceImpl implements IMQMessageService {

    @Resource
    private StreamBridge streamBridge;


    /**
     *
     * @param msg 消息内容
     * @param bindingName exchange绑定的queue名称
     * @return 结果
     */
    @Override
    public ResponseData<Void> sendMsg(Object msg, String bindingName) {
        Message<Object> message = MessageBuilder.withPayload(msg).build();
        streamBridge.send(bindingName, message);
        return ResponseData.success();
    }

    /**
     * 发送延迟消息
     * @param msg 消息
     * @param bindingName exchange绑定的queue名称
     * @param seconds 延迟时长
     * @return 结果
     */
    @Override
    public ResponseData<Void> sendDelayMsg(Object msg, String bindingName, Integer seconds) {
        Message<Object> message = MessageBuilder.withPayload(msg).setHeader("x-delay", seconds * 1000).build();
        streamBridge.send(bindingName, message);
        return ResponseData.success();
    }
}
