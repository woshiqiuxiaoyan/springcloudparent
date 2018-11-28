package com.yan.springcloudprovide;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//@EnableDiscoveryClient
@MapperScan("com.yan.springcloudprovide.mapper")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SpringcloudprovideApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudprovideApplication.class, args);
	}
}
