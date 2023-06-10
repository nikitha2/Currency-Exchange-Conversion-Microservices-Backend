package com.springboot.microservices.currencyconversionservice.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/*
 * Needed to make rest template showup in zipkin. If dont need to showup in zipkin not needed
 * */
@Configuration(proxyBeanMethods = false)
public class RestTemplateConfiguration {
    
    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
