package com.chenchen.manager.filter;

import com.chenchen.common.util.JwtUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 后台网关过滤器
 * @author chenchen
 */
@Component
public class ManagerFilter extends ZuulFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        // 获取请求上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        // 过去request请求
        HttpServletRequest request = currentContext.getRequest();
        // 网关分发放行
        if (request.getMethod().equals("OPIONS")) {
            return null;
        }
        // 登陆请求放行
        if (request.getRequestURL().indexOf("login") > 0) {
            return null;
        }
        // 验证
        String header = request.getHeader("Authorization1");
        if (header != null && !"".equals(header)) {
            if (header.startsWith("Bearer ")) {
                String token = header.substring(7);
                try{
                    Claims claims = jwtUtil.parseJWT(token);
                    String roles = (String) claims.get("roles");
                    if ("admin".equals(roles)) {
                        currentContext.addZuulRequestHeader("Authorization1", header);
                        return null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    // 终止运行
                    currentContext.setSendZuulResponse(false);
                }
            }
        }
        currentContext.setSendZuulResponse(false);
        currentContext.setResponseBody("权限不足");
        currentContext.setResponseStatusCode(403);
        currentContext.getResponse().setContentType("text/html;charset=utf-8");
        return null;
    }
}
