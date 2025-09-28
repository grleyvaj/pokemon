package com.pokemon.demo.application.ports.api.pokemon_detail;

import com.pokemon.demo.application.ports.api.pokemon_detail.ability.AbilityResponse;
import com.pokemon.demo.application.ports.api.pokemon_detail.helds.HeldItemResponse;
import com.pokemon.demo.builder.AbilityBuilder;
import com.pokemon.demo.builder.AbilityResponseBuilder;
import com.pokemon.demo.builder.HeldItemBuilder;
import com.pokemon.demo.builder.HeldItemResponseBuilder;
import com.pokemon.demo.domain.helper.ListMapper;
import com.pokemon.demo.domain.model.Ability;
import com.pokemon.demo.domain.model.HeldItem;
import com.pokemon.demo.domain.model.Pokemon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PokemonDetailResponseMapperTest {

	@InjectMocks
	private PokemonDetailResponseMapper mapper;

	@Mock
	private ListMapper<Ability, AbilityResponse> abilityResponseListMapper;

	@Mock
	private ListMapper<HeldItem, HeldItemResponse> heldItemResponseListMapper;

	@BeforeEach
	void setUp() {
		this.mapper = new PokemonDetailResponseMapper(
		  this.abilityResponseListMapper,
		  this.heldItemResponseListMapper
		);
	}

	@Test
	void test_mapper_is_invoked_then_other_mapper_are_invoked() {
		Pokemon pokemon = new Pokemon(
		  5,
		  "::name::",
		  6,
		  "::location::"
		)
		  .setAbilities(new AbilityBuilder().buildList(2))
		  .setHeldItems(new HeldItemBuilder().buildList(3));

		this.mapper.map(pokemon);

		verify(this.abilityResponseListMapper).map(new AbilityBuilder().buildList(2));
		verify(this.heldItemResponseListMapper).map(new HeldItemBuilder().buildList(3));
	}

	@Test
	void test_when_data_is_mapped_then_response_is_retrieved() {
		when(this.abilityResponseListMapper.map(anyList()))
		  .thenReturn(new AbilityResponseBuilder().buildList(2));
		when(this.heldItemResponseListMapper.map(anyList()))
		  .thenReturn(new HeldItemResponseBuilder().buildList(3));

		Pokemon pokemon = new Pokemon(
		  5,
		  "::name::",
		  6,
		  "::location::"
		)
		  .setAbilities(new AbilityBuilder().buildList(2))
		  .setHeldItems(new HeldItemBuilder().buildList(3));

		PokemonDetailResponse current = this.mapper.map(pokemon);

		assertEquals(new AbilityResponseBuilder().buildList(2), current.getAbilities());
		assertEquals(6, current.getBaseExperience());
		assertEquals(new HeldItemResponseBuilder().buildList(3), current.getHeldItems());
		assertEquals(5, current.getId());
		assertEquals("::name::", current.getName());
		assertEquals("::location::", current.getLocationAreaEncounters());
	}

}