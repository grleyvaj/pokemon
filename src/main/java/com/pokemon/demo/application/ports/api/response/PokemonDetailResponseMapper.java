package com.pokemon.demo.application.ports.api.response;

import com.pokemon.demo.domain.helper.ListMapper;
import com.pokemon.demo.domain.helper.Mapper;
import com.pokemon.demo.application.ports._responses.ability.AbilityResponse;
import com.pokemon.demo.application.ports._responses.helds.HeldItemResponse;
import com.pokemon.demo.domain.model.Ability;
import com.pokemon.demo.domain.model.HeldItem;
import com.pokemon.demo.domain.model.Pokemon;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PokemonDetailResponseMapper implements Mapper<Pokemon, PokemonDetailResponse> {

	private final ListMapper<Ability, AbilityResponse> abilityResponseListMapper;
	private final ListMapper<HeldItem, HeldItemResponse> heldItemResponseListMapper;

	@Override
	public PokemonDetailResponse map(Pokemon model) {
		return new PokemonDetailResponse()
		  .setId(model.getId())
		  .setName(model.getName())
		  .setBaseExperience(model.getBaseExperience())
		  .setLocationAreaEncounters(model.getLocationAreaEncounters())
		  .setAbilities(this.abilityResponseListMapper.map(model.getAbilities()))
		  .setHeldItems(this.heldItemResponseListMapper.map(model.getHeldItems()));
	}

}
