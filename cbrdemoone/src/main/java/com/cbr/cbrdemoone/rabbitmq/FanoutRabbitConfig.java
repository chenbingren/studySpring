package com.cbr.cbrdemoone.rabbitmq;

import com.cbr.cbrdemoone.config.Constants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FanoutRabbitConfig {

    @Bean
    public Queue AMessage() {
        return new Queue("fanout.A");
    }

    @Bean
    public Queue BMessage() {
        return new Queue("fanout.B");
    }

    @Bean
    public Queue CMessage() {
        return new Queue("fanout.C");
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    Binding bindingExchangeA() {
        return BindingBuilder.bind(AMessage()).to(fanoutExchange());
    }

    @Bean
    Binding bindingExchangeB() {
        return BindingBuilder.bind(BMessage()).to(fanoutExchange());
    }

    @Bean
    Binding bindingExchangeC() {
        //不加路由匹配.with(Constants.TOPIC_O4_ROUTE_KEY);，那就是广播模式，所有的都发送
        return BindingBuilder.bind(CMessage()).to(fanoutExchange());
    }

}
