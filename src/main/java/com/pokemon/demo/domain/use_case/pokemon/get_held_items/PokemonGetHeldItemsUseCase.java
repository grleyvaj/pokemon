package com.pokemon.demo.domain.use_case.pokemon.get_held_items;

import com.pokemon.demo.domain.exception.PokemonClientApiException;
import com.pokemon.demo.domain.exception.ResourceNotFoundException;
import com.pokemon.demo.domain.model.HeldItem;
import com.pokemon.demo.domain.repository.PokemonRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PokemonGetHeldItemsUseCase {

	private final PokemonRepository pokemonRepository;

	public List<HeldItem> execute(String pokemonName) throws PokemonClientApiException, ResourceNotFoundException {
		return this.pokemonRepository.getPokemon(pokemonName).getHeldItems();
	}

}
