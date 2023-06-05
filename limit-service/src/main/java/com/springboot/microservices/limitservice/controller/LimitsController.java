package com.springboot.microservices.limitservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.microservices.limitservice.beans.Limits;
import com.springboot.microservices.limitservice.configuration.Configuration;

@RestController
public class LimitsController {
	
	@Autowired
	private Configuration configuration;
	
	
	/** URL: http://localhost:8080/limits will fetch data 
	 * from http://localhost:8888/limits-service/default spring-cloud configuration server
	 * */
	@GetMapping("/limits")
	public Limits retrieveLimits() {
		return new Limits(configuration.getMinimum(),configuration.getMaximum());
	}

}
