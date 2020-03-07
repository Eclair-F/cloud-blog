package com.qw.providerblog.config;

import com.qw.providerblog.token.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Program: cloud-blog
 * @ClassName Interceptor
 * @Description:
 * @Author: Eclair
 * @Create: 2020-03-04 18:52
 * @Version 1.0
 **/
@Component
public class JwtInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String authentication = request.getHeader("Authentication");
        if (authentication != null && !authentication.isEmpty()) {
            Claims claims = JwtUtil.jwtParseToken(authentication);
            if (claims != null && !claims.isEmpty()) {
                String id = claims.getId();
                String role = (String) claims.get("role");
                if (role != null) {
                    request.setAttribute("role", role);
                }
                if (id != null) {
                    request.setAttribute("id", id);
                }
            }
        }
        System.out.println("经过user拦截器");
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) {
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) {
    }
}
