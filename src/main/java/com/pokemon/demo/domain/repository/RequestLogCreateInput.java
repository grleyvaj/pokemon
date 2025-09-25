package com.pokemon.demo.domain.repository;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
@Accessors(chain = true)
public class RequestLogCreateInput {

	private final String originIp;
	private final LocalDateTime requestDate;
	private final String method;
	private Long durationMs;
	private String request;
	private Object responseObj;
	private String response;

	public Optional<Long> getDurationMs() {
		return Optional.ofNullable(durationMs);
	}

	public Optional<String> getRequest() {
		return Optional.ofNullable(request);
	}

	public Optional<String> getResponse() {
		return Optional.ofNullable(response);
	}

	public void addResponse(String response) {
		this.response = response;
	}

}
