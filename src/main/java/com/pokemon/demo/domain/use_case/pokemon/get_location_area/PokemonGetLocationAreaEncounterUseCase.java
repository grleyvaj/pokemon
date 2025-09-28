package com.pokemon.demo.domain.use_case.pokemon.get_location_area;

import com.pokemon.demo.domain.exception.PokemonClientApiException;
import com.pokemon.demo.domain.exception.ResourceNotFoundException;
import com.pokemon.demo.domain.repository.PokemonRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PokemonGetLocationAreaEncounterUseCase {

	private final PokemonRepository pokemonRepository;

	public String execute(String pokemonName) throws PokemonClientApiException, ResourceNotFoundException {
		return this.pokemonRepository.getPokemon(pokemonName).getLocationAreaEncounters();
	}

}
