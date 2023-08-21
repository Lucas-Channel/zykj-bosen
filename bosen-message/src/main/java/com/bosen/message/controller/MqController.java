package com.bosen.message.controller;

import com.bosen.common.constant.response.ResponseData;
import com.bosen.message.api.vo.request.SendMQMessageVO;
import com.bosen.message.service.IMQMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

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
     * @param mqMessageVO 消息内容
     */
    @PostMapping("/sendMessage")
    public ResponseData<Void> sendMessage(@RequestBody @Valid SendMQMessageVO mqMessageVO) {
        return imqMessageService.sendMsg(mqMessageVO);
    }

    /**
     * 发送延迟消息
     *
     * @param mqMessageVO  消息实体
     * @return 结果
     */
    @PostMapping("/sendDelayedMessage")
    public ResponseData<Void> sendDelayedMessage(@RequestBody @Valid SendMQMessageVO mqMessageVO) {
        return imqMessageService.sendDelayMsg(mqMessageVO);// 延迟时间（秒）
    }
}
