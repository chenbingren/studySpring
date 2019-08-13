package com.cbr.cbrdemoone.Filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 过滤器
 */

@Slf4j
@Component
public class MyFilterOne implements Filter {

    @Override
    public void destroy() {
        log.info("-------------MyFilterOne-----time is destory----------------------------");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        log.info("-------------MyFilterOne-----the filter is begin-------url:{}---------------------",request.getRequestURI());
        long start=new Date().getTime();
        chain.doFilter(req, resp);
        log.info("-------------MyFilterOne-----the filter is end----------------耗时为:{}ms------------",(new Date().getTime()-start));
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        log.info("-------------MyFilterOne-----time is init----------------------------");
    }

}