package com.pokemon.demo.domain.repository;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Optional;

@Data
@Accessors(chain = true)
public class RequestLogUpdateInput {

	private Long durationMs;
	private String response;

	public Optional<Long> getDurationMs() {
		return Optional.ofNullable(durationMs);
	}

	public Optional<String> getResponse() {
		return Optional.ofNullable(response);
	}

}
