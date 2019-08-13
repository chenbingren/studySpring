package com.cbr.cbrdemoone.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Lenovo on 2019/7/18.
 */

/**
 * @Data : lombok 自动生成set/get方法，toString方法，equals方法，hashCode方法，不带参数的构造方法
 */
@Component
@Data
public class CbrProperty {

    @Value("${demo.user.name}")
    private String name;

    @Value("${demo.user.account}")
    private String account;

    @Value("${demo.user.description}")
    private String description;

    @Value("#{T(Long).parseLong('${upload.maxsize}')}")
    private Long maxSize;

}
