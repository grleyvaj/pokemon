package com.pokemon.demo.application.ports.webservice.resources.get_ability.response;

import com.pokemon.demo.builder.ResourceResponseSoapBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AbilityResponseSoapTest {

	@Test
	void when_object_is_create_then_object_is_built() {
		AbilityResponseSoap current = new AbilityResponseSoap()
		  .setHidden(Boolean.TRUE)
		  .setSlot(5)
		  .setResource(new ResourceResponseSoapBuilder().build(2));

		assertTrue(current.isHidden());
		assertEquals(5, current.getSlot());
		assertEquals(new ResourceResponseSoapBuilder().build(2), current.getResource());
	}

}