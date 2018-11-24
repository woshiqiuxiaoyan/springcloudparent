package com.yan.springcloudseluthmiya;

import brave.sampler.Sampler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@SpringBootApplication
public class SpringcloudseluthmiyaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudseluthmiyaApplication.class, args);
	}

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping ("/hi")
	public String home(){
		logger.info( "hi is being called");
		return "hi i'm miya!";
	}

	@Autowired
	private RestTemplate restTemplate;

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}


	@RequestMapping("/miya")
	public String info(){
		logger.info("info is being called");
		return restTemplate.getForObject("http://localhost:1113/info",String.class);
	}

	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}

}
