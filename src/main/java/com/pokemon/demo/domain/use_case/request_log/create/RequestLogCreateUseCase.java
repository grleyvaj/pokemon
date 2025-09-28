package com.pokemon.demo.domain.use_case.request_log.create;

import com.pokemon.demo.domain.helper.JsonFormatter;
import com.pokemon.demo.domain.repository.RequestLogCreateInput;
import com.pokemon.demo.domain.repository.RequestLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
public class RequestLogCreateUseCase {

	private final RequestLogRepository repository;

	@Async
	public void execute(RequestLogCreateInput requestLogCreateInput) {

		if(nonNull(requestLogCreateInput.getRequestObj())) {
			requestLogCreateInput.setRequest(
			  JsonFormatter.toJson(requestLogCreateInput.getRequestObj())
			);
		}
		if(nonNull(requestLogCreateInput.getResponseObj())) {
			requestLogCreateInput.setResponse(
			  JsonFormatter.toJson(requestLogCreateInput.getResponseObj())
			);
		}

		this.repository.create(requestLogCreateInput);
	}

}
