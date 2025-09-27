package com.pokemon.demo.domain.use_case.request_log.create;

import com.pokemon.demo.domain.helper.JsonFormatter;
import com.pokemon.demo.domain.model.Method;
import com.pokemon.demo.domain.repository.RequestLogCreateInput;
import com.pokemon.demo.domain.repository.RequestLogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RequestLogCreateUseCaseTest {

	@InjectMocks
	private RequestLogCreateUseCase useCase;

	@Mock
	private RequestLogRepository requestLogRepository;

	@Test
	void when_request_and_response_are_present_then_they_are_serialized_and_saved() {
		Object requestObj = Map.of("name", "::name::");
		Object responseObj = Map.of("id", 123);

		RequestLogCreateInput input = new RequestLogCreateInput("127.0.0.1", Method.DETAIL);
		input.setRequestObj(requestObj);
		input.setResponseObj(responseObj);

		this.useCase.execute(input);

		ArgumentCaptor<RequestLogCreateInput> captor = ArgumentCaptor.forClass(RequestLogCreateInput.class);
		verify(this.requestLogRepository).create(captor.capture());
		RequestLogCreateInput saved = captor.getValue();

		assertThat(saved.getRequest()).hasValue(JsonFormatter.toJson(requestObj));
		assertThat(saved.getResponse()).hasValue(JsonFormatter.toJson(responseObj));
	}

	@Test
	void when_request_and_response_are_null_then_nothing_is_serialized_but_saved_anyway() {
		RequestLogCreateInput input = new RequestLogCreateInput("127.0.0.1", Method.DETAIL);

		this.useCase.execute(input);

		ArgumentCaptor<RequestLogCreateInput> captor = ArgumentCaptor.forClass(RequestLogCreateInput.class);
		verify(this.requestLogRepository).create(captor.capture());
		RequestLogCreateInput saved = captor.getValue();
		assertTrue(saved.getRequest().isEmpty());
		assertTrue(saved.getResponse().isEmpty());
	}

}
