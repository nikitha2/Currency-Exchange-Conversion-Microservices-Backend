package com.springboot.microservices.currencyconversionservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.microservices.currencyconversionservice.dao.CurrencyConversionControllerDaoService;
import com.springboot.microservices.currencyconversionservice.model.CurrencyConversionEntity;

@RestController
public class CurrencyConversionController {
		
	@Autowired
	CurrencyConversionControllerDaoService currencyConversionControllerDaoService;
	
	//http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10
	@GetMapping("/currency-conversion/from/{fromCurrency}/to/{toCurrency}/quantity/{quantity}")
	public CurrencyConversionEntity findByFromAndToAndQuantityWithRestTemplate(
			@PathVariable String fromCurrency,
			@PathVariable String toCurrency,
			@PathVariable BigDecimal quantity) {
		
		return currencyConversionControllerDaoService.findByFromAndToAndQuantityWithRestTemplate(fromCurrency,toCurrency,quantity);
		
	}
	
	@GetMapping("/currency-conversion-feign/from/{fromCurrency}/to/{toCurrency}/quantity/{quantity}")
	public CurrencyConversionEntity findByFromAndToAndQuantityWithFeign(
			@PathVariable String fromCurrency,
			@PathVariable String toCurrency,
			@PathVariable BigDecimal quantity) {
		
		return currencyConversionControllerDaoService.findByFromAndToAndQuantityWithFeign(fromCurrency,toCurrency,quantity);
		
	}

}
