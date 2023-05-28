package com.springboot.microservices.currencyexchangeservice.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import com.springboot.microservices.currencyexchangeservice.model.CurrencyExchangeEntity;
import com.springboot.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;

@Component
public class CurrencyExchangeDaoService {
	
	@Autowired
	CurrencyExchangeRepository currencyExchangeRepository;
	@Autowired
	private Environment environment;
	
	public CurrencyExchangeEntity findByFromCurrencyAndToCurrency(String fromCurrency, String toCurrency){
		CurrencyExchangeEntity currencyExchangeEntity = currencyExchangeRepository.findByFromCurrencyAndToCurrency(fromCurrency, toCurrency);
        currencyExchangeEntity.setEnvironment(environment.getProperty("local.server.port"));
		
		return currencyExchangeEntity;
		
	}
	
	public List<CurrencyExchangeEntity> findByFromCurrency(String fromCurrency){
		return currencyExchangeRepository.findByFromCurrency(fromCurrency);
	}


}
