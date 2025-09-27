package com.pokemon.demo.application.ports.api.pokemon_detail.helds;

import com.pokemon.demo.builder.HeldItemVersionResponseBuilder;
import com.pokemon.demo.builder.ResourceResponseBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HeldItemResponseTest {

	@Test
	void when_object_is_create_then_object_is_built() {
		HeldItemResponse current = new HeldItemResponse()
		  .setItem(new ResourceResponseBuilder().build(2))
		  .setVersionDetails(new HeldItemVersionResponseBuilder().buildList(2));

		assertEquals(current.getItem(), new ResourceResponseBuilder().build(2));
		assertEquals(current.getVersionDetails(), new HeldItemVersionResponseBuilder().buildList(2));
	}


}