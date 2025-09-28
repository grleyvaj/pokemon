package com.pokemon.demo.domain.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pokemon.demo.domain.exception.JsonFormattingException;
import com.pokemon.demo.domain.model.Pokemon;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class JsonFormatterTest {

	@Test
	void test_when_private_constructor_then_throws_exception() {
		Constructor<JsonFormatter> constructor = getConstructor();

		assertThatThrownBy(() -> invokeConstructor(constructor))
		  .isInstanceOf(UnsupportedOperationException.class)
		  .hasMessage("Utility class");
	}

	private Constructor<JsonFormatter> getConstructor() {
		try {
			Constructor<JsonFormatter> constructor = JsonFormatter.class.getDeclaredConstructor();
			constructor.setAccessible(true);
			return constructor;
		} catch(NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

	private void invokeConstructor(Constructor<JsonFormatter> constructor) {
		try {
			constructor.newInstance();
		} catch(InvocationTargetException e) {
			Throwable cause = e.getCause();
			if(cause instanceof RuntimeException re) {
				throw re;
			}
			else {
				throw new RuntimeException(cause);
			}
		} catch(InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	void test_when_toJson_with_valid_object_then_returns_json_string() {
		Pokemon pikachu = new Pokemon(1, "::name::", 25, "::location::");

		String json = JsonFormatter.toJson(pikachu);

		assertThat(json).isEqualTo(
		  "{\"id\":1,\"name\":\"::name::\",\"baseExperience\":25," +
		  "\"locationAreaEncounters\":\"::location::\",\"abilities\":null,\"heldItems\":null}"
		);
	}

	@Test
	void test_when_toJson_with_unserializable_object_then_throws_exception() {
		assertThatThrownBy(this::toJsonUnserializable)
		  .isInstanceOf(JsonFormattingException.class)
		  .hasMessage("Error parsing object to JSON")
		  .hasCauseInstanceOf(JsonProcessingException.class);
	}

	private void toJsonUnserializable() {
		JsonFormatter.toJson(new Object());
	}

}