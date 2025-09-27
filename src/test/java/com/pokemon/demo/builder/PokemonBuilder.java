package com.pokemon.demo.builder;

import com.pokemon.demo.domain.model.Pokemon;

public class PokemonBuilder extends Builder<Pokemon> {

	@Override
	public Pokemon build(int index) {
		return new Pokemon(
		  index,
		  "::name-" + index + "::",
		  index * 2,
		  "::location-" + index + "::"
		)
		  .setAbilities(new AbilityBuilder().buildList(index * 2))
		  .setHeldItems(new HeldItemBuilder().buildList(index * 3));
	}

}
