package com.pokemon.demo.application.ports.webservice.resources.get_ability.response;

import com.pokemon.demo.builder.AbilityResponseSoapBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AbilityListResponseSoapTest {

	@Test
	void when_object_is_create_then_object_is_built() {
		AbilityListResponseSoap current = new AbilityListResponseSoap()
		  .setAbilities(new AbilityResponseSoapBuilder().buildList(2));

		assertEquals(current.getAbilities(), new AbilityResponseSoapBuilder().buildList(2));
	}

}