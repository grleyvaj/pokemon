package com.pokemon.demo.builder;

import com.pokemon.demo.application.ports.api.pokemon_detail.helds.HeldItemResponse;

public class HeldItemResponseBuilder extends Builder<HeldItemResponse> {

	@Override
	public HeldItemResponse build(int index) {
		return new HeldItemResponse()
		  .setItem(new ResourceResponseBuilder().build(index * 2))
		  .setVersionDetails(new HeldItemVersionResponseBuilder().buildList(index));
	}

}
