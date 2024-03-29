package com.bosen.message.api.feign;

import com.bosen.common.constant.response.ResponseData;
import com.bosen.message.api.fallback.MessageFeignFallBack;
import com.bosen.message.api.vo.request.MessageUpsertVO;
import com.bosen.message.api.vo.request.SendMQMessageVO;
import com.bosen.message.api.vo.request.SendWsMessageBatchVO;
import com.bosen.message.api.vo.request.SendWsMessageVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/8/15
 */
@FeignClient(value = "bosen-message", fallback = MessageFeignFallBack.class)
public interface IMessageFeign {

    @PostMapping("/ws/message/send")
    ResponseData<Void> sendWsMessage(@RequestBody @Valid SendWsMessageVO wsMessageVO);

    @PostMapping("/ws/message/sendBatch")
    ResponseData<Void> sendWsMessageBatch(@RequestBody @Valid SendWsMessageBatchVO wsMessageVO);

    /**
     * 新增/修改数据
     *
     * @param messageUpsertVO 实体对象
     * @return 新增结果
     */
    @PostMapping("/common/message/upsert")
    ResponseData<Void> upsert(@RequestBody @Valid MessageUpsertVO messageUpsertVO);

    /**
     * 发送普通消息Rabbitmq
     * bindingName 绑定队列名称
     * @param mqMessageVO 消息内容
     */
    @PostMapping("/mq/sendMessage")
    ResponseData<Void> sendMQMessage(@RequestBody @Valid SendMQMessageVO mqMessageVO);

    /**
     * 发送延迟消息
     *
     * @param mqMessageVO  消息实体
     * @return 结果
     */
    @PostMapping("/mq/sendDelayedMessage")
    ResponseData<Void> sendMQDelayedMessage(@RequestBody @Valid SendMQMessageVO mqMessageVO);
}
