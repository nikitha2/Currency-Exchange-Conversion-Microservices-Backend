package com.springboot.microservices.currencyconversionservice.feignproxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.springboot.microservices.currencyconversionservice.configuration.DbConfiguration;
import feign.auth.BasicAuthRequestInterceptor;

@Configuration
public class FeignClientConfiguration {
	
	@Autowired
	DbConfiguration dbConfiguration;
	
	@Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
         return new BasicAuthRequestInterceptor(
        		 dbConfiguration.getDbUsername(), 
        		 dbConfiguration.getDbPassword()
        		 );
    }
}
