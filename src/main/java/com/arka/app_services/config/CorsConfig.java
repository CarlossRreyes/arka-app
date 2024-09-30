package com.arka.app_services.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {


    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings( CorsRegistry corsRegistry ){
                corsRegistry.addMapping("/administration/**")
                .allowedOrigins("*")
                .allowedMethods("*");
                corsRegistry.addMapping("/auth/**")
                .allowedOrigins("*")
                .allowedMethods("*");
            }
            
        };
    }

}
