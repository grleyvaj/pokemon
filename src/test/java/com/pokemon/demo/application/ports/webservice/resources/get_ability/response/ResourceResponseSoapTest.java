package com.pokemon.demo.application.ports.webservice.resources.get_ability.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResourceResponseSoapTest {

	@Test
	void when_object_is_create_then_object_is_built() {
		ResourceResponseSoap current = new ResourceResponseSoap()
		  .setName("::name::")
		  .setUrl("::url::");

		assertEquals("::name::", current.getName());
		assertEquals("::url::", current.getUrl());
	}

}