package com.pokemon.demo.domain.use_case.pokemon.get_abilities;

import com.pokemon.demo.builder.AbilityBuilder;
import com.pokemon.demo.builder.PokemonBuilder;
import com.pokemon.demo.domain.model.Ability;
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
class PokemonGetAbilitiesUseCaseTest {

	@InjectMocks
	private PokemonGetAbilitiesUseCase useCase;

	@Mock
	private PokemonRepository pokemonRepository;

	@Test
	void when_use_case_is_invoked_then_pokemon_is_searched() {
		when(this.pokemonRepository.getPokemon(anyString())).thenReturn(new PokemonBuilder().build(2));

		this.useCase.execute("::name::");

		verify(this.pokemonRepository).getPokemon("::name::");
	}

	@Test
	void when_pokemon_is_found_then_pokemon_abilities_are_retrieved() {
		when(this.pokemonRepository.getPokemon(anyString()))
		  .thenReturn(
			new PokemonBuilder().build(2)
			  .setAbilities(new AbilityBuilder().buildList(3))
		  );

		List<Ability> abilities = this.useCase.execute("::name::");

		assertEquals(abilities, new AbilityBuilder().buildList(3));
	}

}