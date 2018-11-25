package com.yan.springcloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@SpringBootApplication
public class SpringcloudgatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudgatewayApplication.class, args);
	}

}
