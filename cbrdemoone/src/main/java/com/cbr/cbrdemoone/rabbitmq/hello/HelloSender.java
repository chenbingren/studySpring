package com.cbr.cbrdemoone.rabbitmq.hello;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class HelloSender {

	//rabbitTemplate是springboot 提供的默认实现
	@Autowired
	private AmqpTemplate rabbitTemplate;

	/**
	 * 消息发送
	 * @param context
	 */
	public void send(String context) {
		System.out.println("Sender : " + context);
		this.rabbitTemplate.convertAndSend("hello", context);
	}

}