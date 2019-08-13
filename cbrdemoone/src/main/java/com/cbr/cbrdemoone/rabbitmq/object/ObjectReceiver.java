package com.cbr.cbrdemoone.rabbitmq.object;

import com.cbr.cbrdemoone.mode.UserInfo;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "object")
public class ObjectReceiver {

    @RabbitHandler
    public void process(UserInfo user) {
        System.out.println("Receiver object : " + user);
    }

}
