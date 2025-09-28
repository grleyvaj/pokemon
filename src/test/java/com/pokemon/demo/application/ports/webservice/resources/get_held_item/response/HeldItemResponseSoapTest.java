package com.pokemon.demo.application.ports.webservice.resources.get_held_item.response;

import com.pokemon.demo.builder.HeldItemVersionResponseSoapBuilder;
import com.pokemon.demo.builder.ResourceResponseSoapBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HeldItemResponseSoapTest {

	@Test
	void when_object_is_create_then_object_is_built() {
		HeldItemResponseSoap current = new HeldItemResponseSoap()
		  .setItem(new ResourceResponseSoapBuilder().build(2))
		  .setVersionDetails(new HeldItemVersionResponseSoapBuilder().buildList(2));

		assertEquals(current.getItem(), new ResourceResponseSoapBuilder().build(2));
		assertEquals(current.getVersionDetails(), new HeldItemVersionResponseSoapBuilder().buildList(2));
	}

}