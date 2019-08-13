package com.cbr.cbrdemoone.rabbitmq.topic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 发送消息
 */
@Slf4j
@Component
public class TopicSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send(String exchange_name,String routingKey,String context) {
		log.info("-----------------------send----------------------------发送消息："+context);
		System.out.println("exchange_name=="+exchange_name+"，routingKey="+routingKey+",Sender : " + context);
		//中间那个参数是：routing_key
		this.rabbitTemplate.convertAndSend(exchange_name, routingKey, context);
	}


}