package com.pokemon.demo.application.ports.api.pokemon_detail;

import com.pokemon.demo.builder.AbilityResponseBuilder;
import com.pokemon.demo.builder.HeldItemResponseBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PokemonDetailResponseTest {

	@Test
	void when_object_is_create_then_object_is_built() {
		PokemonDetailResponse current = new PokemonDetailResponse()
		  .setAbilities(new AbilityResponseBuilder().buildList(2))
		  .setBaseExperience(3)
		  .setHeldItems(new HeldItemResponseBuilder().buildList(3))
		  .setId(5)
		  .setName("::name::")
		  .setLocationAreaEncounters("::location::");

		assertEquals(new AbilityResponseBuilder().buildList(2), current.getAbilities());
		assertEquals(3, current.getBaseExperience());
		assertEquals(new HeldItemResponseBuilder().buildList(3), current.getHeldItems());
		assertEquals(5, current.getId());
		assertEquals("::name::", current.getName());
		assertEquals("::location::", current.getLocationAreaEncounters());
	}

}