package com.pokemon.demo.domain.use_case.pokemon.get_id;

import com.pokemon.demo.domain.exception.PokemonClientApiException;
import com.pokemon.demo.domain.exception.ResourceNotFoundException;
import com.pokemon.demo.domain.repository.PokemonRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PokemonGetIdUseCase {

	private final PokemonRepository pokemonRepository;

	public int execute(String pokemonName) throws PokemonClientApiException, ResourceNotFoundException {
		return this.pokemonRepository.getPokemon(pokemonName).getId();
	}

}
