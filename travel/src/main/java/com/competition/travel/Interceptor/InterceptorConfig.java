/*
package com.competition.travel.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private InitInterceptor initInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(initInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns("/web/comLogin","/index.html")
        .excludePathPatterns("/assets/**");
    }
}
*/
