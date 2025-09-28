package com.pokemon.demo.application.ports.webservice.resources.get_held_item.response;

import com.pokemon.demo.builder.HeldItemResponseSoapBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HeldItemListResponseSoapTest {

	@Test
	void when_object_is_create_then_object_is_built() {
		HeldItemListResponseSoap current = new HeldItemListResponseSoap()
		  .setHeldItems(new HeldItemResponseSoapBuilder().buildList(2));

		assertEquals(current.getHeldItems(), new HeldItemResponseSoapBuilder().buildList(2));
	}

}