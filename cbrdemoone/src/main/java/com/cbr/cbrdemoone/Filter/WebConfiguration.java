package com.cbr.cbrdemoone.Filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 自定义过滤器
 */
@Slf4j
@Configuration
public class WebConfiguration {
    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }

    /**
     * 这个方法是用来注册过滤器的，服务启动时候运行
     * @return
     */
    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        log.info("=================testFilterRegistration===================");
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CbrFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("CbrFilter");
        registration.setOrder(1);
        return registration;
    }

    /**
     * 自定义的过滤器
     * 有三个周期=====初始化，调用，销毁
     */
    public class CbrFilter implements Filter {
		@Override
		public void destroy() {
            log.info("=================Filter destroy===================");
		}

		@Override
		public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterChain)
				throws IOException, ServletException {
            log.info("=================Filter doFilter===================");
			HttpServletRequest request = (HttpServletRequest) srequest;


			System.out.println("this is MyFilter,url :"+request.getRequestURI());
			filterChain.doFilter(srequest, sresponse);
		}

		@Override
		public void init(FilterConfig arg0) throws ServletException {
            log.info("=================Filter init===================");
		}
    }
}



