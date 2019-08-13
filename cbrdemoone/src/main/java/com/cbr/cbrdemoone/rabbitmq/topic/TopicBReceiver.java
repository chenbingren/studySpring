package com.cbr.cbrdemoone.rabbitmq.topic;

import com.cbr.cbrdemoone.config.Constants;
import com.cbr.cbrdemoone.rabbitmq.TopicRabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = Constants.CBR_TOPIC_B_QUEUE_NAME)
public class TopicBReceiver {

    @RabbitHandler
    public void process(String message) {
        log.info("---------------------------topic.B ----接收消息------------------------------------"+message);
    }

}
