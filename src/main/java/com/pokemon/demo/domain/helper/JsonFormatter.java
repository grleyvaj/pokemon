package com.pokemon.demo.domain.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemon.demo.domain.exception.JsonFormattingException;

public class JsonFormatter {

	private JsonFormatter() {
		throw new UnsupportedOperationException("Utility class");
	}

	public static String toJson(Object object) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(object);
		} catch(JsonProcessingException e) {
			throw new JsonFormattingException("Error parsing object to JSON", e);
		}
	}

}
