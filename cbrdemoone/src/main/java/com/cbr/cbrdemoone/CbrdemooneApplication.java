package com.cbr.cbrdemoone;

import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@SpringBootApplication
//在启动类上面加上@EnableScheduling即可开启定时  【spring-boot-starter包里包含】
@EnableScheduling
public class CbrdemooneApplication {

	public static void main(String[] args) {
		SpringApplication.run(CbrdemooneApplication.class, args);
	}


	/**
	 * 大文件上传   重连机制
	 * tomcatEmbedded这段代码是为了解决【上传文件大于10M出现连接重置】的问题。 此异常内容GlobalException也捕获不到。
	 *
	 * 【cbr:测试发现没效果】
	 * @return
	 */
	//Tomcat large file upload connection reset
	@Bean
	public TomcatServletWebServerFactory tomcatEmbedded() {
		log.info("-------------------文件大于10M出现连接重置的-------------------------------------");
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
		tomcat.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {
			if ((connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?>)) {
				//-1 means unlimited
				((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);
			}
		});
		return tomcat;
	}
}
