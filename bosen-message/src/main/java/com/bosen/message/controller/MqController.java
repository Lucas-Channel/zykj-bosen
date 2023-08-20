package com.bosen.message.controller;

import com.bosen.common.constant.response.ResponseData;
import com.bosen.message.service.IMQMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/8/20
 */
@Slf4j
@RestController
@RequestMapping("/mq")
public class MqController {
    @Resource
    private IMQMessageService imqMessageService;
    /**
     * 发送普通消息Rabbitmq
     * bindingName 绑定队列名称
     * @param msg 消息内容
     */
    @GetMapping("/sendMessage/{msg}/{bindingName}")
    public ResponseData<Void> sendMessage(@PathVariable("msg") String msg, @PathVariable("bindingName") String bindingName) {
        log.info(bindingName + "发送消息: " + msg);
        return imqMessageService.sendMsg(msg, bindingName);
    }

    /**
     * 发送延迟消息
     *
     * @param message  消息实体
     * @return 结果
     */
    @GetMapping("/sendDelayedMessage")
    public ResponseData<Void> sendDelayedMessage(String message, String bindingName) {
        log.info("发送延时消息: " + LocalDateTime.now() + "  " + message);
        return imqMessageService.sendDelayMsg(message, bindingName, 10);// 延迟时间（秒）
    }
}
