package com.springboot.microservices.Gateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
	
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		return builder
				.routes()
				.route(p-> p.path("/currency-exchange/**")
						.uri("lb://CURRENCY-EXCHANGE-SERVICE"))
				.route(p-> p.path("/currency-conversion/**")
						.uri("lb://CURRENCY-CONVERSION-SERVICE"))
				.route(p-> p.path("/currency-conversion-feign/**")
						.uri("lb://CURRENCY-CONVERSION-SERVICE"))
				
				//Example to show how headers and params can be added
				.route(p-> p.path("/get/**")
						.filters(f-> f.addRequestHeader("Username", "dummy_username")
								.addRequestHeader("Password", "dummy_password")
								.addRequestParameter("Param", "dummy_param"))
						.uri("http://httpbin.org:80"))
			 
				/* Example to show how URL path can be altered
				http://localhost:8765/currency-conversion/from/USD/to/INR/quantity/20 
				change to 
				http://localhost:8765/currency-conversion-new/from/USD/to/INR/quantity/20 
				
				Segment -> saying append what ever is after / using functional programming
				*/
				.route(p-> p.path("/currency-conversion-new/**")
						.filters(f-> f.rewritePath("/currency-conversion-new/(?<segement>.*)", "/currency-conversion/${segement}"))
						.uri("lb://CURRENCY-CONVERSION-SERVICE"))
				.build();
	}
}
