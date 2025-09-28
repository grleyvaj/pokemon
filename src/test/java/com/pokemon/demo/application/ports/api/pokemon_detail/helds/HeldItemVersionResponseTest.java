package com.pokemon.demo.application.ports.api.pokemon_detail.helds;

import com.pokemon.demo.builder.ResourceBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HeldItemVersionResponseTest {

	@Test
	void when_object_is_create_then_object_is_built() {
		HeldItemVersionResponse current = new HeldItemVersionResponse()
		  .setRarity(3)
		  .setVersion(new ResourceBuilder().build(2));

		assertEquals(3, current.getRarity());
		assertEquals(new ResourceBuilder().build(2), current.getVersion());
	}

}