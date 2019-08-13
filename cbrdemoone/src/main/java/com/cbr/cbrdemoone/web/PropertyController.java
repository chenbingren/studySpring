package com.cbr.cbrdemoone.web;

import com.cbr.cbrdemoone.config.CbrProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 *  阿炳学习笔记：
 *  一、
 *  使用 @Controller  有返回的地方要加上 ：@ResponseBody 不然会报错
 *  使用 @RestController 不需要加上@ResponseBody  ==  只需要类添加 @RestController 即可，默认类中的方法都会以json的格式返回
 */
@Slf4j
@RestController
public class PropertyController {
    @Autowired
    CbrProperty cbrProperty;

    @GetMapping("/hello")
    public String sayHello() {
        log.info("我是info日志");
        log.error("我是error日志");
        return "Hello，Spring Boot！ 我是阿炳";
    }

    @GetMapping("/hello2")

    public String sayHello2() {
        return "Hello， 阿炳是我4， myname= " + cbrProperty.getName() + " myaccount =  "
                + cbrProperty.getAccount() + " ,mywriter = "+cbrProperty.getDescription();
    }

}
