package com.yan.springcloudconfigclient.propertysource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;

/**
 * <p>类名:MyPropertiesHandler </p>
 * <p>描述:</p>
 * 创建方式 IntelliJ IDEA.
 * 创建人: qxy
 * 创建日期: 2018/11/22
 * 创建时间: 19:05
 */
public class MyPropertiesHandler implements PropertySourceLoader {

    private static final Logger logger = LoggerFactory.getLogger(MyPropertiesHandler.class);

    @Override
    public String[] getFileExtensions() {
        return new String[] {"properties","xml"};
    }

    @Override
    public List<PropertySource<?>> load(String name,Resource resource) throws IOException {
        //        if (profile == null) {
        Properties properties = getProperties(resource);
        if (!properties.isEmpty()) {
            PropertiesPropertySource propertiesPropertySource = new PropertiesPropertySource(name,properties);

            return Collections.singletonList(propertiesPropertySource);
            //                return propertiesPropertySource;
        }
        return null;
    }

/*    @Override
    public PropertySource<?> load(String name,Resource resource,String profile) throws IOException {

    }*/

    private Properties getProperties(Resource resource) {
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = resource.getInputStream();
            properties.load(new InputStreamReader(inputStream,"utf-8"));
            inputStream.close();
        } catch (IOException e) {
            logger.error("load inputstream failure...",e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("close IO failure ....",e);
                }
            }
        }
        return properties;
    }
}