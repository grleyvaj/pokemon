package com.pokemon.demo.application.ports.webservice.resources.get_held_item.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HeldItemsRequestSoapTest {

	@Test
	void when_object_is_create_then_object_is_built() {
		HeldItemsRequestSoap request = new HeldItemsRequestSoap().setName("::name::");

		assertEquals("::name::", request.getName());
	}

}