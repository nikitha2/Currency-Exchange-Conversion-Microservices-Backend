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
		
		String port= environment.getProperty("local.server.port");
		String host= environment.getProperty("HOSTNAME");
		String version= getClass().getPackage().getImplementationVersion();
		String version1= "v12";


        currencyExchangeEntity.setEnvironment(port+" "+version+"/"+version1+" "+host);
		
        
		return currencyExchangeEntity;
		
	}
	
	public List<CurrencyExchangeEntity> findByFromCurrency(String fromCurrency){
		return currencyExchangeRepository.findByFromCurrency(fromCurrency);
	}


}
