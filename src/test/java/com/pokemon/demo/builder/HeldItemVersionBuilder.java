package com.pokemon.demo.builder;

import com.pokemon.demo.domain.model.HeldItemVersion;

public class HeldItemVersionBuilder extends Builder<HeldItemVersion> {

	@Override
	public HeldItemVersion build(int index) {
		return new HeldItemVersion()
		  .setVersion(new ResourceBuilder().build(index * 2))
		  .setRarity(index);
	}

}
