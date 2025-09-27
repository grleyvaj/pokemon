package com.pokemon.demo.application.ports.api.pokemon_detail.resource;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResourceResponseTest {

	@Test
	void when_object_is_create_then_object_is_built() {
		ResourceResponse request = new ResourceResponse().setName("::name::");

		assertEquals("::name::", request.getName());
	}

}