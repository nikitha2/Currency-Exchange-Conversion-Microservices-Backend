package com.springboot.microservices.currencyexchangeservice.runners;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.springboot.microservices.currencyexchangeservice.model.CurrencyExchangeEntity;
import com.springboot.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;

/**
 * CommandLineRunner: Used to run commands when application starts
 * @author Nikitha
 *
 */

@Component
public class CurrencyExchangeCommandLineRunner implements CommandLineRunner {

	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		// Perform insert
		currencyExchangeRepository.save(new CurrencyExchangeEntity(new BigDecimal(65),"USD","INR","TEST"));
		currencyExchangeRepository.save(new CurrencyExchangeEntity(new BigDecimal(80),"EUR","INR","TEST"));
		currencyExchangeRepository.save(new CurrencyExchangeEntity(new BigDecimal(23),"AUS","INR","TEST"));
		System.out.println("----------------------DB populated --------------------------");
	}

}
