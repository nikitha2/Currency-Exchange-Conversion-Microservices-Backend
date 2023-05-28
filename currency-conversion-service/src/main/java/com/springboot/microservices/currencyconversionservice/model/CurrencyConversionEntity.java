package com.springboot.microservices.currencyconversionservice.model;

import java.math.BigDecimal;

public class CurrencyConversionEntity {

    private Long id;
	
	private String fromCurrency;
	
	private String toCurrency;

	private BigDecimal conversionMultiple;
	
	private BigDecimal quantity;
	
	private BigDecimal totalCalculatedAmount;

	private String environment;

	public CurrencyConversionEntity() {
		
	}
	
	public CurrencyConversionEntity(Long id, String from, 
			String to, 
			BigDecimal conversionMultiple,
			BigDecimal quantity, 
			BigDecimal totalCalculatedAmount,
			String environment) {
		super();
		this.id=id;
		this.fromCurrency = from;
		this.toCurrency = to;
		this.conversionMultiple = conversionMultiple;
		this.quantity=quantity;
		this.totalCalculatedAmount=totalCalculatedAmount;
		this.environment=environment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFrom() {
		return fromCurrency;
	}

	public void setFrom(String from) {
		this.fromCurrency = from;
	}

	public String getTo() {
		return toCurrency;
	}

	public void setTo(String to) {
		this.toCurrency = to;
	}

	public BigDecimal getConversionMultiple() {
		return conversionMultiple;
	}

	public void setConversionMultiple(BigDecimal conversionMultiple) {
		this.conversionMultiple = conversionMultiple;
	}
	
	public BigDecimal getTotalCalculatedAmount() {
		return totalCalculatedAmount;
	}

	public void setTotalCalculatedAmount(BigDecimal totalCalculatedAmount) {
		this.totalCalculatedAmount = totalCalculatedAmount;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

}
