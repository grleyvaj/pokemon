package com.pokemon.demo.domain.repository;

import com.pokemon.demo.domain.model.Method;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Optional;

@Data
@Accessors(chain = true)
public class RequestLogCreateInput {

	private final String originIp;
	private final Method method;
	private Long durationMs;
	private String request;
	private Object requestObj;
	private String response;
	private Object responseObj;

	public Optional<Long> getDurationMs() {
		return Optional.ofNullable(durationMs);
	}

	public Optional<String> getRequest() {
		return Optional.ofNullable(request);
	}

	public Optional<String> getResponse() {
		return Optional.ofNullable(response);
	}

}
