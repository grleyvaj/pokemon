package com.pokemon.demo.builder;

import com.pokemon.demo.application.ports.api.pokemon_detail.ability.AbilityResponse;

public class AbilityResponseBuilder extends Builder<AbilityResponse> {

	@Override
	public AbilityResponse build(int index) {
		return new AbilityResponse()
		  .setHidden(index % 2 != 0)
		  .setSlot(index)
		  .setAbility(new ResourceResponseBuilder().build(index * 5));
	}

}
