package com.improveusa.application.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;


@Configuration
public class SpringBeans {

    @Bean public RequestContextListener requestContextListener(){
        return new RequestContextListener();
    }
}
