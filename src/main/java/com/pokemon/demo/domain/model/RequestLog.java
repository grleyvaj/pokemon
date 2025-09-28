package com.pokemon.demo.domain.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class RequestLog {

	private final String id;
	private final String originIp;
	private final LocalDateTime requestDate;
	private final Method method;
	private Long durationMs;
	private String request;
	private String response;


}
