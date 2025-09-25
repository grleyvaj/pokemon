package com.pokemon.demo.infrastructure.api.dtos.ability;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pokemon.demo.infrastructure.api.dtos.resource.NamedAPIResource;
import lombok.Data;

@Data
public class PokemonAbility {

	@JsonProperty("is_hidden")
	public boolean isHidden;

	@JsonProperty("slot")
	public int slot;

	@JsonProperty("ability")
	public NamedAPIResource ability;

}
