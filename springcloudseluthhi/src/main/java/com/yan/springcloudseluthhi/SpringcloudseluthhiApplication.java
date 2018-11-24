package com.yan.springcloudseluthhi;

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

@SpringBootApplication
@RestController
public class SpringcloudseluthhiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudseluthhiApplication.class,args);
    }

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @RequestMapping ("/hi")
    public String callHome() {
        logger.info("calling trace service-hi  ");
        return restTemplate.getForObject("http://localhost:1114/miya",String.class);
    }


    @RequestMapping ("/info")
    public String info() {
        logger.info("calling trace service-hi ");
        return "i'm service-hi";
    }

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }

}
