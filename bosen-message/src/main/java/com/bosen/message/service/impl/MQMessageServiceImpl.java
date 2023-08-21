package com.bosen.message.service.impl;

import com.bosen.common.constant.response.ResponseData;
import com.bosen.message.api.vo.request.SendMQMessageVO;
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
     * @param mqMessageVO 消息内容
     * @return 结果
     */
    @Override
    public ResponseData<Void> sendMsg(SendMQMessageVO mqMessageVO) {
        Message<String> message = MessageBuilder.withPayload(mqMessageVO.getMsg()).build();
        streamBridge.send(mqMessageVO.getBindingName(), message);
        return ResponseData.success();
    }

    /**
     * 发送延迟消息
     * @param mqMessageVO 消息
     * @return 结果
     */
    @Override
    public ResponseData<Void> sendDelayMsg(SendMQMessageVO mqMessageVO) {
        Message<String> message = MessageBuilder.withPayload(mqMessageVO.getMsg()).setHeader("x-delay", mqMessageVO.getDelaySeconds() * 1000).build();
        streamBridge.send(mqMessageVO.getBindingName(), message);
        return ResponseData.success();
    }
}
