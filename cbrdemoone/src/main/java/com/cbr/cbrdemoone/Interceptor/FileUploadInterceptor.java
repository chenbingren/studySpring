package com.cbr.cbrdemoone.Interceptor;

import com.cbr.cbrdemoone.config.CbrProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 此拦截器作用是拦截到附件上传超过了10M,则抛出异常让Advice去捕获
 */
@Slf4j
@Component
public class FileUploadInterceptor implements HandlerInterceptor {
    @Autowired
    CbrProperty cbrProperty;

    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) {
        log.info("-----------------------FileUploadInterceptor-----------------------------");
        if (request != null &&
                ServletFileUpload.isMultipartContent(request)) {
            ServletRequestContext requestContext = new ServletRequestContext(request);
            long requestSize = requestContext.contentLength();
            log.info("上传数据大小：{}MB", requestSize/1024/1024);
            if ((requestSize/1024/1024) > cbrProperty.getMaxSize()) {
                throw new MaxUploadSizeExceededException(cbrProperty.getMaxSize());
            }
        }
        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler, Exception ex) throws Exception {
    }
}