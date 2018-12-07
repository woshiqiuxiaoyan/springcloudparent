package com.yan.springcloudprovide;

import com.yan.springcloudservice.redis.ILock;
import com.yan.springcloudservice.redis.util.RedisLock;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableDiscoveryClient
@MapperScan("com.yan.springcloudprovide.mapper")
@ComponentScan("com.yan")
@SpringBootApplication
public class SpringcloudprovideApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudprovideApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringcloudprovideApplication.class);
	}



	@Bean
	@ConditionalOnMissingClass
	public ILock initLock(){
 		LoggerFactory.getLogger(this.getClass()).info("====初始化redis锁===");
		ILock lock=new RedisLock();
		return lock;
	}



}
