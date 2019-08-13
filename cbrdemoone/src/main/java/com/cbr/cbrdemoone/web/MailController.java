package com.cbr.cbrdemoone.web;

import com.cbr.cbrdemoone.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * 邮件发送一共提供了以下五种方式：文本、html、附件、图片、模板
 *
 * 发送失败
 * 因为各种原因，总会有邮件发送失败的情况，比如：邮件发送过于频繁、网络异常等。在出现这种情况的时候，我们一般会考虑重新重试发送邮件，会分为以下几个步骤来实现：
 *   1、接收到发送邮件请求，首先记录请求并且入库。
 *   2、调用邮件发送接口发送邮件，并且将发送结果记录入库。
 *   3、启动定时系统扫描时间段内，未发送成功并且重试次数小于3次的邮件，进行再次发送
 *
 * 异步发送
 * 很多时候邮件发送并不是我们主业务必须关注的结果，比如通知类、提醒类的业务可以允许延时或者失败。
 * 这个时候可以采用异步的方式来发送邮件，加快主交易执行速度，
 * 在实际项目中可以采用MQ发送邮件相关参数，监听到消息队列之后启动发送邮件。
 */
@Slf4j
@RestController
public class MailController {

    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

    /**
     * 文本邮件
     * @throws Exception
     */
    @GetMapping("/testSimpleMail")
    public void testSimpleMail() throws Exception {
        log.info("--------testSimpleMail begin--------------");
        mailService.sendSimpleMail("1845031249@qq.com","test simple mail","cbr: hello this is simple mail");
        log.info("--------testSimpleMail end--------------");
    }

    /**
     * HTML邮件
     * @throws Exception
     */
    @GetMapping("/testHtmlMail")
    public void testHtmlMail() throws Exception {
        log.info("--------testHtmlMail begin--------------");
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>hello world cbr ! 这是一封html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail("1845031249@qq.com","test simple mail",content);
        log.info("--------testHtmlMail end--------------");
    }

    /**
     * 有附件邮件
     */
    @GetMapping("/sendAttachmentsMail")
    public void sendAttachmentsMail() {
        log.info("--------sendAttachmentsMail begin--------------");
        String filePath="D:\\CBR\\TXT\\工作备注.txt";
        mailService.sendAttachmentsMail("1845031249@qq.com", "主题：带附件的邮件", "有附件，请查收！", filePath);
        log.info("--------sendAttachmentsMail end--------------");
    }

    /**
     * 有图片邮件
     */
    @GetMapping("/sendInlineResourceMail")
    public void sendInlineResourceMail() {
        log.info("--------sendInlineResourceMail begin--------------");

        String rscId = "neo006";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "D:\\CBR\\工作文件与资料\\录音与拍照记录\\cb.jpg";

        mailService.sendInlineResourceMail("1845031249@qq.com", "主题：这是有图片的邮件", content, imgPath, rscId);

        log.info("--------sendInlineResourceMail end--------------");
    }

    /**
     * 使用邮件模板方式
     */
    @GetMapping("/sendTemplateMail")
    public void sendTemplateMail() {
        log.info("--------sendTemplateMail begin--------------");

        //创建邮件正文
        Context context = new Context();
        context.setVariable("id", "006");
        String emailContent = templateEngine.process("emailTemplate", context);
        mailService.sendHtmlMail("1845031249@qq.com","主题：这是模板邮件",emailContent);

        log.info("--------sendTemplateMail begin--------------");
    }
}
