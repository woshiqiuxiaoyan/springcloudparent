package com.yan.springcloudzuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <p>类名:YanFilter </p>
 * <p>描述:</p>
 * 创建方式 IntelliJ IDEA.
 * 创建人: qxy
 * 创建日期: 2018/11/21
 * 创建时间: 20:16
 */
@Component
public class YanFilter extends ZuulFilter{

    private Logger logger= LoggerFactory.getLogger(YanFilter.class);
    /**
    *  pre：路由之前
    *  routing：路由之时
    *  post： 路由之后
    *  error：发送错误调用
    * @return
    */
    @Override
    public String filterType() {
        logger.info("====路由之前====");
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 这里可以写逻辑判断，是否要过滤，本文true,永远过滤。
     * 比如登录
     * @return
     */
    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String pathInfo =  request.getRequestURI();
        if(pathInfo.contains("provide")){
            return true;
        }
        logger.info(String.format("====pathInfo:%s",pathInfo));
        return false;
    }

    /**
     * 过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问。
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        logger.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        Object accessToken = request.getParameter("token");
        if(accessToken == null) {
            logger.warn("token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                ctx.getResponse().getWriter().write("token is empty");
            }catch (Exception e){}

            return null;
        }
        logger.info("ok");
        return null;

    }
}
