package com.yan.springcloudprovide.contorller;

import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.List;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.RequestFacade;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.tomcat.util.http.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>类名:MyProvide </p>
 * <p>描述:</p>
 * 创建方式 IntelliJ IDEA.
 * 创建人: qxy
 * 创建日期: 2018/10/29
 * 创建时间: 17:27
 */
@RestController
public class MyProvide {


    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping ("/index")
    public String inex() {
        List<String> services = discoveryClient.getServices();
        //        service discoveryClient.getInstances("springcloudprovide");
        services.forEach((e) -> {
            System.err.println(e);
        });
        return "hello yan";
    }

    @RequestMapping ("/mytest")
    public void test(HttpServletRequest request) throws Exception {

        RequestFacade requestFacade = (RequestFacade)request;
        Class requetCls = requestFacade.getClass();
        Field field = requetCls.getDeclaredField("request");
        field.setAccessible(true);
        Object requestinner = field.get(requestFacade);
        Request request1 = (Request)requestinner;
        Class request1Cls = request1.getClass();
        Field field1 = request1Cls.getDeclaredField("coyoteRequest");
        field1.setAccessible(true);
        org.apache.coyote.Request request2 = (org.apache.coyote.Request)field1.get(request1);
        Field field2 =  request2.getClass().getDeclaredField("parameters");
        field2.setAccessible(true);
        Parameters parameters=(Parameters)field2.get(request2);

        parameters.addParameter("yandaye","yandaye");

        field2.set(request2,parameters);
        field1.set(request1,request2);
        field.set(requestFacade,request1);
        request=(HttpServletRequest) requestFacade;




        Enumeration<String> params =   request.getParameterNames();

        while (params.hasMoreElements()) {
            String param = params.nextElement();
            System.out.println("name : "+param+" value"+requestFacade.getParameter(param));
         }

        return;
    }

}
