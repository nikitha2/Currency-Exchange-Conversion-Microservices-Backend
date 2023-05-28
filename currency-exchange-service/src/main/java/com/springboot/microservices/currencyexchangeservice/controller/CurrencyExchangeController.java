package com.springboot.microservices.currencyexchangeservice.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.microservices.currencyexchangeservice.configuration.DbConfiguration;
import com.springboot.microservices.currencyexchangeservice.dao.CurrencyExchangeDaoService;
import com.springboot.microservices.currencyexchangeservice.model.CurrencyExchangeEntity;
import com.springboot.microservices.currencyexchangeservice.model.DbConfigurationModal;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	CurrencyExchangeDaoService currencyExchangeDaoService;
	
	@Autowired
	private DbConfiguration dbConfiguration;
	
	//Example: 
	@GetMapping("/currency-exchange/from/{fromCurrency}/to/{toCurrency}")
	public CurrencyExchangeEntity findByFromCurrencyAndToCurrency(@PathVariable String fromCurrency, @PathVariable String toCurrency){
		return currencyExchangeDaoService.findByFromCurrencyAndToCurrency(fromCurrency, toCurrency);
	}
	
	@GetMapping("/currency-exchange/from/{fromCurrency}")
	public List<CurrencyExchangeEntity> findByFromCurrency(@PathVariable String fromCurrency){
		return currencyExchangeDaoService.findByFromCurrency(fromCurrency);
	}
	
	@GetMapping("/currency-exchange/db-credentials")
	public DbConfigurationModal findDbCredentials(){
		return new DbConfigurationModal(dbConfiguration.getUsername(),dbConfiguration.getPassword());
	}

}
