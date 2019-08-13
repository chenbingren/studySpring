package com.cbr.cbrdemoone.web;

import com.cbr.cbrdemoone.config.Constants;
import com.cbr.cbrdemoone.mode.UserInfo;
import com.cbr.cbrdemoone.rabbitmq.fanout.FanoutSender;
import com.cbr.cbrdemoone.rabbitmq.hello.HelloSender;
import com.cbr.cbrdemoone.rabbitmq.many.NeoSender;
import com.cbr.cbrdemoone.rabbitmq.many.NeoSender2;
import com.cbr.cbrdemoone.rabbitmq.object.ObjectSender;
import com.cbr.cbrdemoone.rabbitmq.topic.TopicSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 */
@Slf4j
@RestController
public class RabbitMQController {

    @Autowired
    private HelloSender helloSender;
    @Autowired
    private ObjectSender objectSender;

    @Autowired
    private NeoSender neoSender;

    @Autowired
    private NeoSender2 neoSender2;

    @Autowired
    private TopicSender topicSender;

    @Autowired
    private FanoutSender fanoutSender;

    /**
     * 单一的发送消息队列
     * @throws Exception
     */
    @GetMapping("/rab1")
    public void rab1() throws Exception {
        log.info("----------------------开始发送消息队列[字符串]---rab1-----------------------");
        String context = "炳任  --  hello  -- 当前时间：" + new Date();
        helloSender.send(context);
    }

    /**
     * 单一的对象发送消息队列
     * @throws Exception
     */
    @GetMapping("/rab2")
    public void rab2() throws Exception {
        log.info("----------------------开始发送消息队列[对象]----rab2----------------------");
        UserInfo user=new UserInfo();
        user.setUid(111);
        user.setUsername("neo");
        user.setPassword("123456");
        objectSender.send(user);
    }

    /**
     * 多的发送消息队列
     *
     * 一个发送者，N个接受者,经过测试会均匀的将消息发送到N个接收者中
     */
    @GetMapping("/rab3")
    public void rab3() throws Exception {
        log.info("----------------------开始发送消息队列[一对多]----rab3----------------------");
        for (int i=0;i<100;i++){
            neoSender.send(i);
        }
    }

    /**
     * 多的发送消息队列
     *
     * 和一对多一样，接收端仍然会均匀接收到消息
     */
    @GetMapping("/rab4")
    public void rab4() throws Exception {
        log.info("----------------------开始发送消息队列[多对多]----rab4----------------------");
        for (int i=0;i<100;i++){
            neoSender.send(i);
            neoSender2.send(i);
        }
    }

    /**
     * 自由的绑定不同的队列
     * topic 是RabbitMQ中最灵活的一种方式，可以根据routing_key自由的绑定不同的队列
     *
     * 交换机和路由器区别
     * 交换机，也就是相当于多了网线接口的意思。比如你十个人上网，可是口没有那么多，那么插上交换机就可以。
     * 你要上网，用交换机就不可以了。这个时候你必须要用到路由器，因为这个会自动拨号，并且传输无线信号。
     * 交换机发生在网络的第二层数据链路层，
     * 而路由器发生在第三层网络层。
     * 这个区别是两者各自工作方式的根本区别。
     * 路由器可以根据IP地址寻找下一个设备，可以处理TCPIP协议，
     * 交换机是根据MAC地址寻址的。
     */
    @GetMapping("/rab5")
    public void rab5() throws Exception {
        log.info("----------------------开始发送消息队列[自由的绑定]----rab5----------------------");
        String context = "这是第一条消息1111111111";
        topicSender.send(Constants.CBR_TOPIC_EXCHANGE_NAME,"cbr.topic.AA",context);

        context = "这是第二条消息";
        topicSender.send(Constants.CBR_TOPIC_EXCHANGE_NAME,"cbr.topic.AA.BB",context);

        context = "这是第三条消息";
        topicSender.send(Constants.CBR_TOPIC_EXCHANGE_NAME,"topic.AA",context);

        context = "这是第四条消息";
        topicSender.send(Constants.CBR_TOPIC_EXCHANGE_NAME,"AA.BB.cbr.DD",context);

        context = "这是五条消息";
        topicSender.send(Constants.CBR_TOPIC_EXCHANGE_NAME,"xy.topic.BB.BB",context);

        context = "这是六条消息";
        topicSender.send(Constants.CBR_TOPIC_EXCHANGE_NAME,"topic",context);
    }

    /**
     * 自由的绑定不同的队列
     * topic 是RabbitMQ中最灵活的一种方式，可以根据routing_key自由的绑定不同的队列
     */
    @GetMapping("/rab6")
    public void rab6() throws Exception {
        log.info("----------------------开始发送消息队列[广播模式]----rab6----------------------");
        fanoutSender.send();
    }

}
