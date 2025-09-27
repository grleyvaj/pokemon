package com.pokemon.demo.application.ports.webservice.resources.get_ability.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AbilityRequestSoapTest {

	@Test
	void when_object_is_create_then_object_is_built() {
		AbilityRequestSoap request = new AbilityRequestSoap().setName("::name::");

		assertEquals("::name::", request.getName());
	}

}