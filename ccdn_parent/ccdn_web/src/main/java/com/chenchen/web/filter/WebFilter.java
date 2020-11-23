package com.chenchen.web.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 网关过滤器
 * @author chenchen
 */
@Component
public class WebFilter extends ZuulFilter {

    /**
     * 过滤方式
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 执行顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否开启此过滤器
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤执行方法
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {

        // 获取request上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        // 获取request请求
        HttpServletRequest request = currentContext.getRequest();
        // 获取头信息
        String header = request.getHeader("Authorization1");
        // 转发头信息
        if(header != null && !"".equals(header)) {
            currentContext.addZuulRequestHeader("Authorization1", header);
            System.out.println(request.getHeader("Authorization1"));
        }

        return null;
    }
}
