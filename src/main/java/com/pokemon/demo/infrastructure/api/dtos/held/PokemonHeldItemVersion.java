package com.pokemon.demo.infrastructure.api.dtos.held;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pokemon.demo.infrastructure.api.dtos.resource.NamedAPIResource;
import lombok.Data;

@Data
public class PokemonHeldItemVersion {

	@JsonProperty("version")
	private NamedAPIResource version;

	@JsonProperty("rarity")
	private int rarity;

}
