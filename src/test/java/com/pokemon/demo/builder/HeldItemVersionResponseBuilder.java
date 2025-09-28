package com.pokemon.demo.builder;

import com.pokemon.demo.application.ports.api.pokemon_detail.helds.HeldItemVersionResponse;

public class HeldItemVersionResponseBuilder extends Builder<HeldItemVersionResponse> {

	@Override
	public HeldItemVersionResponse build(int index) {
		return new HeldItemVersionResponse()
		  .setVersion(new ResourceBuilder().build(index * 2))
		  .setRarity(index);
	}

}
