package com.yan.springcloudprovide;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableDiscoveryClient
@MapperScan("com.yan.springcloudprovide.mapper")
@SpringBootApplication
@EnableCaching
public class SpringcloudprovideApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudprovideApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringcloudprovideApplication.class);
	}


}
