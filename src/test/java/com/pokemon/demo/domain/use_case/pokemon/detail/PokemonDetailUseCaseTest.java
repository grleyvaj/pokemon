package com.pokemon.demo.domain.use_case.pokemon.detail;

import com.pokemon.demo.builder.PokemonBuilder;
import com.pokemon.demo.domain.model.Pokemon;
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
class PokemonDetailUseCaseTest {

	@InjectMocks
	private PokemonDetailUseCase useCase;

	@Mock
	private PokemonRepository pokemonRepository;

	@Test
	void when_use_case_is_invoked_then_pokemon_is_searched() {
		this.useCase.execute("::name::");

		verify(this.pokemonRepository).getPokemon("::name::");
	}

	@Test
	void when_pokemon_is_found_then_pokemon_is_retrieved() {
		when(this.pokemonRepository.getPokemon(anyString())).thenReturn(new PokemonBuilder().build(2));

		Pokemon current = this.useCase.execute("::name::");

		assertEquals(current, new PokemonBuilder().build(2));
	}

}