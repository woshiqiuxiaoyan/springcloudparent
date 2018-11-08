package com.yan.springcloudhelloservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringcloudhelloserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudhelloserviceApplication.class, args);
	}
}
