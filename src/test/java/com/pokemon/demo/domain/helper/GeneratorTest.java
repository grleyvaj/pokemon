package com.pokemon.demo.domain.helper;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class GeneratorTest {

	@Test
	void test_when_private_constructor_then_throws_exception() throws Exception {
		Constructor<Generator> constructor = Generator.class.getDeclaredConstructor();
		constructor.setAccessible(true);

		InvocationTargetException thrown = assertThrows(
		  InvocationTargetException.class,
		  constructor::newInstance,
		  "Calling private constructor should throw InvocationTargetException"
		);

		assertTrue(thrown.getCause() instanceof UnsupportedOperationException);
		assertEquals("Utility class", thrown.getCause().getMessage());
	}

	@Test
	void test_when_ulid_generates_with_non_null_then_unique_values_are_generate() {
		String ulid1 = Generator.ulid();
		String ulid2 = Generator.ulid();

		assertNotNull(ulid1, "ULID must not be null");
		assertFalse(ulid1.isEmpty(), "ULID must not be empty");

		assertNotNull(ulid2, "ULID must not be null");
		assertFalse(ulid2.isEmpty(), "ULID must not be empty");

		assertNotEquals(ulid1, ulid2, "Each ULID must be unique");
	}

}
