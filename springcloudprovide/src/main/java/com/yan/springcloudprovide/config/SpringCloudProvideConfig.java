package com.yan.springcloudprovide.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: QXY
 * @classDescribe:
 * @createTime: 2019/1/23
 * @version: 1.0
 */
@Component
@Data
@Slf4j
public class SpringCloudProvideConfig implements InitializingBean {

    @Value("${com.random.value}")
    private String randomValue;


    @Override
    public void afterPropertiesSet() throws Exception {
        log.info(String.format("===============random value : %s",randomValue));
    }
}
