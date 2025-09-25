package com.pokemon.demo.domain.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFormatter {

	public static String toJson(Object object) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(object);
		} catch(JsonProcessingException e) {
			throw new RuntimeException("Parser error", e);
		}
	}

}
