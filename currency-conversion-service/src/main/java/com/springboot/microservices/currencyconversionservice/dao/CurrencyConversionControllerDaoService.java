package com.springboot.microservices.currencyconversionservice.dao;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.HashMap;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.springboot.microservices.currencyconversionservice.configuration.DbConfiguration;
import com.springboot.microservices.currencyconversionservice.feignproxy.CustomerExchangeProxy;
import com.springboot.microservices.currencyconversionservice.model.CurrencyConversionEntity;

@Component
public class CurrencyConversionControllerDaoService {

	@Autowired
	DbConfiguration dbConfiguration;
	
	@Autowired
    private RestTemplate restTemplate;
	
	@Autowired
	CustomerExchangeProxy customerExchangeProxy;

	HttpHeaders createHeaders(String username, String password) {
		return new HttpHeaders() {
			private static final long serialVersionUID = 1L;
			{
				String auth = username + ":" + password;
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				set("Authorization", authHeader);
			}
		};
	}
	
	public CurrencyConversionEntity findByFromAndToAndQuantityWithRestTemplate(String fromCurrency, String toCurrency,
			BigDecimal quantity) {

		HashMap<String, String> uriVariable = new HashMap<>();
		uriVariable.put("fromCurrency", fromCurrency);
		uriVariable.put("toCurrency", toCurrency);

		String uri = "http://localhost:8000/currency-exchange/from/{fromCurrency}/to/{toCurrency}";
		
		//If don't need restTemplate calls to shown on zipkin, simply use new RestTemplate().exchange(...);
		ResponseEntity<CurrencyConversionEntity> responseEntity = restTemplate.exchange(uri, HttpMethod.GET,
				new HttpEntity<Object>(createHeaders(dbConfiguration.getUsername(), dbConfiguration.getPassword())),
				CurrencyConversionEntity.class, uriVariable);

		// Can be used if no authentication needed. In other words, if spring-security is not used
        /** ResponseEntity<CurrencyConversionEntity> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/from/{fromCurrency}/to/{toCurrency}", 
				CurrencyConversionEntity.class,
				uriVariable); 
		**/

		CurrencyConversionEntity responseEntityBody = responseEntity.getBody();
		return new CurrencyConversionEntity(responseEntityBody.getId(), responseEntityBody.getFrom(),
				responseEntityBody.getTo(), responseEntityBody.getConversionMultiple(), quantity,
				quantity.multiply(responseEntityBody.getConversionMultiple()), responseEntityBody.getEnvironment());
	}
	
	
	
	public CurrencyConversionEntity findByFromAndToAndQuantityWithFeign(String fromCurrency, String toCurrency,
			BigDecimal quantity) {
		HashMap<String, String> uriVariable = new HashMap<>();
		uriVariable.put("fromCurrency", fromCurrency);
		uriVariable.put("toCurrency", toCurrency);

		CurrencyConversionEntity responseEntity = customerExchangeProxy.findByFromCurrencyAndToCurrency(fromCurrency, toCurrency);

		return new CurrencyConversionEntity(responseEntity.getId(), responseEntity.getFrom(),
				responseEntity.getTo(), responseEntity.getConversionMultiple(), quantity,
				quantity.multiply(responseEntity.getConversionMultiple()), responseEntity.getEnvironment());
	}

}
