package com.cbr.cbrdemoone.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 拦截器与过滤器不同，拦截器必须装配才会有效果
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter{
    @Autowired
    public FileUploadInterceptor fileUploadInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //装配拦截器
        registry.addInterceptor(fileUploadInterceptor);
    }
}