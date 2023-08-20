package com.bosen.message.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.function.Consumer;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/8/20
 */
@Slf4j
@Component
public class MqConsumer {
    /**
     * mq接收ackMessage消息/手动ack确认
     **/
    @Bean
    Consumer<Message<Object>> ackMessage() {
        log.info("ackMessage-初始化订阅");
        return obj -> {
            Channel channel = obj.getHeaders().get(AmqpHeaders.CHANNEL, Channel.class);
            Long deliveryTag = obj.getHeaders().get(AmqpHeaders.DELIVERY_TAG, Long.class);
            try {
                log.info("ackMessage-消息接收成功：" + obj.getPayload());
                //业务逻辑处理
                //ack确认
                channel.basicAck(deliveryTag, false);
            } catch (Exception e) {
                //重新回队列-true则重新入队列，否则丢弃或者进入死信队列。
//                    channel.basicReject(deliveryTag, true);
                log.error(e.getMessage());
            }

        };
    }

    /**
     * mq接收normal消息
     **/
    @Bean
    Consumer<Object> normal() {
        log.info("normal-初始化订阅");
        return obj -> {
            log.info("normal-消息接收成功：" + obj);
            //业务逻辑处理
        };
    }


    /**
     * mq接收延时消息
     * Messaging 发送实体消息接收实体消息
     **/
    @Bean
    Consumer<Message<Object>> delayMessage() {
        log.info("delay-初始化订阅");
        return obj -> {
            Object payload = obj.getPayload();
            log.info("delay-消息接收成功：" + LocalDateTime.now() + "  " + payload);
            //业务逻辑处理
        };
    }
}
