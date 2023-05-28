package com.springboot.microservices.currencyexchangeservice.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.springboot.microservices.currencyexchangeservice.model.CurrencyExchangeEntity;

@Component
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchangeEntity, Long> {
	
	public CurrencyExchangeEntity findByFromCurrencyAndToCurrency(String fromCurrency, String toCurrency);
	
	public List<CurrencyExchangeEntity> findByFromCurrency(String fromCurrency);

}
