package com.pokemon.demo.domain.use_case.pokemon.get_base_experience;

import com.pokemon.demo.builder.AbilityBuilder;
import com.pokemon.demo.builder.HeldItemBuilder;
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
class PokemonGetBaseExperienceUseCaseTest {

	@InjectMocks
	private PokemonGetBaseExperienceUseCase useCase;

	@Mock
	private PokemonRepository pokemonRepository;

	@Test
	void when_use_case_is_invoked_then_pokemon_is_searched() {
		when(this.pokemonRepository.getPokemon(anyString())).thenReturn(new PokemonBuilder().build(2));

		this.useCase.execute("::name::");

		verify(this.pokemonRepository).getPokemon("::name::");
	}

	@Test
	void when_pokemon_is_found_then_pokemon_is_retrieved() {
		int expected_experience = 3;
		when(this.pokemonRepository.getPokemon(anyString()))
		  .thenReturn(
			new Pokemon(
			  2,
			  "::name::",
			  3,
			  "::location::"
			)
			  .setAbilities(new AbilityBuilder().buildList(2))
			  .setHeldItems(new HeldItemBuilder().buildList(3))
		  );

		int experience = this.useCase.execute("::name::");

		assertEquals(experience, expected_experience);
	}

}