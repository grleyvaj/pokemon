package com.pokemon.demo.application.ports.api.pokemon_detail.ability;

import com.pokemon.demo.builder.ResourceResponseBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AbilityResponseTest {

	@Test
	void when_object_is_create_then_object_is_built() {
		AbilityResponse current = new AbilityResponse()
		  .setHidden(Boolean.TRUE)
		  .setSlot(5)
		  .setAbility(new ResourceResponseBuilder().build(2));

		assertTrue(current.isHidden());
		assertEquals(5, current.getSlot());
		assertEquals(new ResourceResponseBuilder().build(2), current.getAbility());
	}

}