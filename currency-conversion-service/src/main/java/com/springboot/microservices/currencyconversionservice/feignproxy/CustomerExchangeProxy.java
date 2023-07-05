package com.springboot.microservices.currencyconversionservice.feignproxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.springboot.microservices.currencyconversionservice.model.CurrencyConversionEntity;

@Component
@FeignClient(name = "currency-exchange-service",
//			 url = "${CURRENCY_EXCHANGE_SERVICE_HOST:http://localhost}:8000", // Will search for CURRENCY_EXCHANGE_SERVICE_HOST in env variables. If not found will use http:localhost..
             url = "${CURRENCY_EXCHANGE_URI:http://localhost}:8000", // Custom environment variable. Defined in Kubernetes. (deployment.yaml file)

			 configuration = FeignClientConfiguration.class)
public interface CustomerExchangeProxy {
	

	@GetMapping("/currency-exchange/from/{fromCurrency}/to/{toCurrency}")
	public CurrencyConversionEntity findByFromCurrencyAndToCurrency(@PathVariable String fromCurrency,
			@PathVariable String toCurrency);
}
