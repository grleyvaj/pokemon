package com.pokemon.demo.application.configuration.rest;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "apis")
public class ApisProperties {

	private ApiClientProperties pokemon;

}