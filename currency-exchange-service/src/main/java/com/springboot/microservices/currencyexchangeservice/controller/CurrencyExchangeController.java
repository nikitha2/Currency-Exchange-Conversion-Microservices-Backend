package com.springboot.microservices.currencyexchangeservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

	@Autowired
	CurrencyExchangeDaoService currencyExchangeDaoService;
	
	@Autowired
	private DbConfiguration dbConfiguration;
	
	//Example: 
	@GetMapping("/currency-exchange/from/{fromCurrency}/to/{toCurrency}")
	public CurrencyExchangeEntity findByFromCurrencyAndToCurrency(@PathVariable String fromCurrency, @PathVariable String toCurrency){
		logger.info("/currency-exchange/from/{fromCurrency}/to/{toCurrency} call with  fromCurrency: {} and toCurrency: "+fromCurrency,toCurrency);
		return currencyExchangeDaoService.findByFromCurrencyAndToCurrency(fromCurrency, toCurrency);
	}
	
	@GetMapping("/currency-exchange/from/{fromCurrency}")
	public List<CurrencyExchangeEntity> findByFromCurrency(@PathVariable String fromCurrency){
		logger.info("/currency-exchange/from/{fromCurrency} call with  fromCurrency: {}", fromCurrency);
		return currencyExchangeDaoService.findByFromCurrency(fromCurrency);
	}
	
	@GetMapping("/currency-exchange/db-credentials")
	public DbConfigurationModal findDbCredentials(){
		logger.info("/currency-exchange/db-credentials call made");
		return new DbConfigurationModal(dbConfiguration.getDbUsername(),dbConfiguration.getDbPassword());
	}

}