/*
package com.competition.travel.Interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class InitInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("userName");
        System.out.println("pre" + user + request.getRequestURI());
        if(user == null){
            request.setAttribute("msg", "请先登录");
            System.out.println("请先登录");
            */
/**request.getRequestDispatcher("/index.html").forward(request, response);*//*

        }else {
            System.out.println("登录成功");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Object user = request.getSession().getAttribute("userName");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Object user = request.getSession().getAttribute("userName");
    }
}
*/
