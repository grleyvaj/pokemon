package com.pokemon.demo.domain.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class JsonFormattingExceptionTest {

	@Test
	void test_when_construct_with_message_and_cause_then_fields_are_set() {
		Throwable cause = new RuntimeException("root cause");
		JsonFormattingException ex = new JsonFormattingException("error message", cause);

		assertThat(ex).hasMessage("error message");
		assertThat(ex).hasCause(cause);
	}

}