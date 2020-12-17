package com.xiangban.data_platform.config;

import com.xiangban.data_platform.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {


    @Bean
    LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

//        registry.addInterceptor(loginInterceptor()).addPathPatterns("/api/v1/user/*/**").addPathPatterns("/api/v1/quenstionnaire/*/**")
//                .excludePathPatterns("/api/v1/user/login").excludePathPatterns("/api/v1/user/Alternately");
//        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
