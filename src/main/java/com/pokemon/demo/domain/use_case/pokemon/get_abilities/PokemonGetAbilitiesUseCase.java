package com.pokemon.demo.domain.use_case.pokemon.get_abilities;

import com.pokemon.demo.domain.exception.PokemonClientApiException;
import com.pokemon.demo.domain.exception.ResourceNotFoundException;
import com.pokemon.demo.domain.model.Ability;
import com.pokemon.demo.domain.repository.PokemonRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PokemonGetAbilitiesUseCase {

	private final PokemonRepository pokemonRepository;

	public List<Ability> execute(String pokemonName) throws PokemonClientApiException, ResourceNotFoundException {
		return this.pokemonRepository.getPokemon(pokemonName).getAbilities();
	}

}
