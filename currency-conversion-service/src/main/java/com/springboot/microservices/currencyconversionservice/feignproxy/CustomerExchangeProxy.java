package com.springboot.microservices.currencyconversionservice.feignproxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.springboot.microservices.currencyconversionservice.model.CurrencyConversionEntity;

@Component
@FeignClient(name = "currency-exchange-service", configuration = FeignClientConfiguration.class)
public interface CustomerExchangeProxy {
	

	@GetMapping("/currency-exchange/from/{fromCurrency}/to/{toCurrency}")
	public CurrencyConversionEntity findByFromCurrencyAndToCurrency(@PathVariable String fromCurrency,
			@PathVariable String toCurrency);
}
