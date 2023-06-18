package com.springboot.microservices.currencyexchangeservice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("db-credentials")
public class DbConfiguration {
	private String db_username;
	private String db_password;
	
	public String getDbUsername() {
		return db_username;
	}
	public void setDbUsername(String username) {
		this.db_username = username;
	}
	public String getDbPassword() {
		return db_password;
	}
	public void setDbPassword(String password) {
		this.db_password = password;
	}
}
