package com.pokemon.demo.builder;

import com.pokemon.demo.domain.model.Ability;

public class AbilityBuilder extends Builder<Ability> {

	@Override
	public Ability build(int index) {
		return new Ability(
		  index % 2 != 0,
		  index
		).setResource(new ResourceBuilder().build(index * 2));
	}

}
