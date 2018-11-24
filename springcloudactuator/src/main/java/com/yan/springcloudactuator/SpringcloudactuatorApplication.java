package com.yan.springcloudactuator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringcloudactuatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudactuatorApplication.class, args);
	}
}
