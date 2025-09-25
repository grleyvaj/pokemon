package com.pokemon.demo.domain.use_case.request_log;

import com.pokemon.demo.domain.helper.JsonFormatter;
import com.pokemon.demo.domain.repository.RequestLogCreateInput;
import com.pokemon.demo.domain.repository.RequestLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;

@RequiredArgsConstructor
public class RequestLogCreateUseCase {

	private final RequestLogRepository repository;

	@Async
	public void execute(RequestLogCreateInput requestLogCreateInput) {
		requestLogCreateInput.addResponse(
		  JsonFormatter.toJson(requestLogCreateInput.getResponseObj())
		);

		this.repository.create(requestLogCreateInput);
	}

}
