package com.pokemon.demo.application.ports.webservice.resources.get_held_item.response;

import com.pokemon.demo.builder.ResourceResponseSoapBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HeldItemVersionResponseSoapTest {

	@Test
	void when_object_is_create_then_object_is_built() {
		HeldItemVersionResponseSoap current = new HeldItemVersionResponseSoap()
		  .setRarity(3)
		  .setVersion(new ResourceResponseSoapBuilder().build(2));

		assertEquals(3, current.getRarity());
		assertEquals(new ResourceResponseSoapBuilder().build(2), current.getVersion());
	}

}