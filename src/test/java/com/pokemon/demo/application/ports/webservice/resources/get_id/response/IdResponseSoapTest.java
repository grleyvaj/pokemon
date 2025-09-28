package com.pokemon.demo.application.ports.webservice.resources.get_id.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IdResponseSoapTest {

	@Test
	void when_object_is_create_then_object_is_built() {
		IdResponseSoap current = new IdResponseSoap().setId(3);

		assertEquals(3, current.getId());
	}

}