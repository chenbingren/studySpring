package com.cbr.cbrdemoone.rabbitmq;

import com.cbr.cbrdemoone.config.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * topic 是RabbitMQ中最灵活的一种方式，可以根据routing_key自由的绑定不同的队列
 * 首先对topic规则配置，这里使用两个队列来测试
 */
@Slf4j
@Configuration
public class TopicRabbitConfig {

    @Bean
    public Queue queueTopic() {
        return new Queue(Constants.CBR_TOPIC_QUEUE_NAME);
    }

    @Bean
    public Queue queueTopicA() {
        return new Queue(Constants.CBR_TOPIC_A_QUEUE_NAME);
    }

    /**
     * durable 默认是true
     * @return
     */
    @Bean
    public Queue queueTopicB() {
        return new Queue(Constants.CBR_TOPIC_B_QUEUE_NAME,true);
    }

    @Bean
    public Queue queueTopicC() {
        return new Queue(Constants.CBR_TOPIC_C_QUEUE_NAME,true);
    }

    @Bean
    public Queue queueTopicD() {
        return new Queue(Constants.CBR_TOPIC_D_QUEUE_NAME,true);
    }

    /**
     * 新建  交换机 cbrTopicExchange
     * @return
     */
    @Bean
    TopicExchange exchange() {
        TopicExchange topicExchange = new TopicExchange(Constants.CBR_TOPIC_EXCHANGE_NAME);
        //消息延迟推送
//        topicExchange.setDelayed(true);
        return new TopicExchange(Constants.CBR_TOPIC_EXCHANGE_NAME);
    }

    /**
     * 绑定交换机------匹配多种  #号表示模糊匹配多个字符  *号表示迷糊匹配一个字符
     * @return
     */
    @Bean
    public Binding binding1ExchangeTopics() {
        log.info("---------------bindingExchangeTopic---------------------"+Constants.TOPIC_O1_ROUTE_KEY);
        //匹配到后就跳到指定的队列----queueTopicA()
        return BindingBuilder.bind(queueTopicA()).to(exchange()).with(Constants.TOPIC_O1_ROUTE_KEY);
    }

    /**
     * 绑定交换机------匹配多种  #号表示模糊匹配多个字符  *号表示迷糊匹配一个字符
     * @return
     */
    @Bean
    public Binding binding2ExchangeTopics() {
        log.info("---------------bindingExchangeTopic---------------------"+Constants.TOPIC_O2_ROUTE_KEY);
        return BindingBuilder.bind(queueTopicB()).to(exchange()).with(Constants.TOPIC_O2_ROUTE_KEY);
    }

    /**
     * 绑定交换机------匹配多种  #号表示模糊匹配多个字符  *号表示迷糊匹配一个字符
     * @return
     */
    @Bean
    public Binding binding3ExchangeTopics() {
        log.info("---------------bindingExchangeTopic---------------------"+Constants.TOPIC_O3_ROUTE_KEY);
        return BindingBuilder.bind(queueTopicC()).to(exchange()).with(Constants.TOPIC_O3_ROUTE_KEY);
    }

    /**
     * 绑定交换机------匹配多种  #号表示模糊匹配多个字符  *号表示迷糊匹配一个字符
     * @return
     */
    @Bean
    public Binding binding4ExchangeTopics() {
        log.info("---------------bindingExchangeTopic---------------------"+Constants.TOPIC_O4_ROUTE_KEY);
        return BindingBuilder.bind(queueTopicD()).to(exchange()).with(Constants.TOPIC_O4_ROUTE_KEY);
    }

    /**
     * 绑定交换机------匹配多种  #号表示模糊匹配多个字符  *号表示迷糊匹配一个字符
     * @return
     */
    @Bean
    public Binding binding5ExchangeTopics() {
        log.info("---------------bindingExchangeTopic---------------------"+Constants.TOPIC_O5_ROUTE_KEY);
        return BindingBuilder.bind(queueTopic()).to(exchange()).with(Constants.TOPIC_O5_ROUTE_KEY);
    }
}
