package com.pokemon.demo.domain.use_case.pokemon.detail;

import com.pokemon.demo.domain.exception.PokemonClientApiException;
import com.pokemon.demo.domain.exception.ResourceNotFoundException;
import com.pokemon.demo.domain.model.Pokemon;
import com.pokemon.demo.domain.repository.PokemonRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PokemonDetailUseCase {

	private final PokemonRepository pokemonRepository;

	public Pokemon execute(String pokemonName) throws PokemonClientApiException, ResourceNotFoundException {
		return this.pokemonRepository.getPokemon(pokemonName);
	}

}
