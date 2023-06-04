package com.springboot.microservices.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {

	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

	
	/**
	 * retry5Times will retry 5 times as
	 * declared in application.properties
	 **/
	@GetMapping("currency-exchange/sample-api")
	// default will try the call 3times if it fails.
	//@Retry(name = "default", fallbackMethod = "hardcodedResponse") 
	@Retry(name = "retry5Times", fallbackMethod = "hardcodedResponse") 
	public String sampleApi() {
		logger.info("---- SampleApi call recieved");

		// Make call to a dummy rest api that is bound to fail as it does not exist
		ResponseEntity<String> response = new RestTemplate()
				.getForEntity("https://localhost:8080/dummy-api-that-will-fail", String.class);
		return response.getBody();
	}

	public String hardcodedResponse(Exception ex) {
		logger.info("---- hardcodedResponse call recieved");
		return "hardcoded - FallBack - Response";
	}

}
