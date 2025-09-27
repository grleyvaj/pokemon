package com.pokemon.demo.application.ports.webservice.resources.get_name.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NameRequestSoapTest {

	@Test
	void when_object_is_create_then_object_is_built() {
		NameRequestSoap request = new NameRequestSoap().setName("::name::");

		assertEquals("::name::", request.getName());
	}

}