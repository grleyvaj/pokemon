package com.pokemon.demo.application.configuration.properties;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class ApiClientProperties {

	private String baseUrl;
	private int connectTimeoutMs;
	private int readTimeoutMs;
	private int writeTimeoutMs;
	private int maxRetries;
	private int delayMs;

}