package com.pokemon.demo.domain.use_case.pokemon.get_held_items;

import com.pokemon.demo.builder.HeldItemBuilder;
import com.pokemon.demo.builder.PokemonBuilder;
import com.pokemon.demo.domain.model.HeldItem;
import com.pokemon.demo.domain.repository.PokemonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PokemonGetHeldItemsUseCaseTest {

	@InjectMocks
	private PokemonGetHeldItemsUseCase useCase;

	@Mock
	private PokemonRepository pokemonRepository;

	@Test
	void when_use_case_is_invoked_then_pokemon_is_searched() {
		when(this.pokemonRepository.getPokemon(anyString())).thenReturn(new PokemonBuilder().build(2));

		this.useCase.execute("::name::");

		verify(this.pokemonRepository).getPokemon("::name::");
	}

	@Test
	void when_pokemon_is_found_then_pokemon_held_items_are_retrieved() {
		when(this.pokemonRepository.getPokemon(anyString()))
		  .thenReturn(
			new PokemonBuilder().build(2)
			  .setHeldItems(new HeldItemBuilder().buildList(3))
		  );

		List<HeldItem> items = this.useCase.execute("::name::");

		assertEquals(items, new HeldItemBuilder().buildList(3));
	}

}