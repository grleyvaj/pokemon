package com.pokemon.demo.application.ports.webservice.resources.get_name.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NameResponseSoapTest {

	@Test
	void when_object_is_create_then_object_is_built() {
		NameResponseSoap current = new NameResponseSoap().setName("::name::");

		assertEquals("::name::", current.getName());
	}

}