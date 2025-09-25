package com.pokemon.demo.domain.repository;

import com.pokemon.demo.domain.exception.PokemonClientApiException;
import com.pokemon.demo.domain.exception.ResourceNotFoundException;
import com.pokemon.demo.domain.model.Pokemon;

public interface PokemonRepository {

	Pokemon getPokemon(String pokemonName) throws ResourceNotFoundException, PokemonClientApiException;

}
