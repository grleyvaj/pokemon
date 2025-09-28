package com.pokemon.demo.domain.use_case.pokemon.get_location_area;

import com.pokemon.demo.builder.PokemonBuilder;
import com.pokemon.demo.domain.repository.PokemonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PokemonGetLocationAreaEncounterUseCaseTest {

	@InjectMocks
	private PokemonGetLocationAreaEncounterUseCase useCase;

	@Mock
	private PokemonRepository pokemonRepository;

	@Test
	void when_use_case_is_invoked_then_pokemon_is_searched() {
		when(this.pokemonRepository.getPokemon(anyString())).thenReturn(new PokemonBuilder().build(2));

		this.useCase.execute("::name::");

		verify(this.pokemonRepository).getPokemon("::name::");
	}

	@Test
	void when_pokemon_is_found_then_pokemon_location_area_id_is_retrieved() {
		String expected_location_area = "::location-2::";
		when(this.pokemonRepository.getPokemon(anyString()))
		  .thenReturn(new PokemonBuilder().build(2));

		String location_area = this.useCase.execute("::name::");

		assertEquals(location_area, expected_location_area);
	}

}