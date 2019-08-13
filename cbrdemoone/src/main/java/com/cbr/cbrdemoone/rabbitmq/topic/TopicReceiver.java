package com.cbr.cbrdemoone.rabbitmq.topic;

import com.cbr.cbrdemoone.config.Constants;
import com.cbr.cbrdemoone.rabbitmq.TopicRabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by Lenovo on 2019/7/26.
 */

@Slf4j
@Component
@RabbitListener(queues = Constants.CBR_TOPIC_QUEUE_NAME )
public class TopicReceiver {

    @RabbitHandler
    public void process(String message) {
        log.info("---------------------------topics----接收消息------------------------------------"+message);
    }
}
