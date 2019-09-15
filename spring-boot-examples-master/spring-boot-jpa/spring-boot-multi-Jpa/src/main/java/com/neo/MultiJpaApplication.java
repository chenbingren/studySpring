package com.neo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 该案例是测试在同一个应用中，使用了两套数据库，针对大公司！
 */
@SpringBootApplication
public class MultiJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiJpaApplication.class, args);
	}
}
