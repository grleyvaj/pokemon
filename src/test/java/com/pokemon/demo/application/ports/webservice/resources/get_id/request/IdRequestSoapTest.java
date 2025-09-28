package com.pokemon.demo.application.ports.webservice.resources.get_id.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IdRequestSoapTest {

	@Test
	void when_object_is_create_then_object_is_built() {
		IdRequestSoap request = new IdRequestSoap().setName("::name::");

		assertEquals("::name::", request.getName());
	}

}