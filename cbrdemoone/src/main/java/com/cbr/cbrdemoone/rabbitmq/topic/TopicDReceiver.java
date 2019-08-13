package com.cbr.cbrdemoone.rabbitmq.topic;

import com.cbr.cbrdemoone.config.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = Constants.CBR_TOPIC_D_QUEUE_NAME)
public class TopicDReceiver {

    @RabbitHandler
    public void process(String message) {
        log.info("---------------------------topicD---接收消息------------------------------------"+message);
    }

}
