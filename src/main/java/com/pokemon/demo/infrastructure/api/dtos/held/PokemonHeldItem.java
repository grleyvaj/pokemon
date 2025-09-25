package com.pokemon.demo.infrastructure.api.dtos.held;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pokemon.demo.infrastructure.api.dtos.resource.NamedAPIResource;
import lombok.Data;

import java.util.List;

@Data
public class PokemonHeldItem {

	@JsonProperty("item")
	public NamedAPIResource item;

	@JsonProperty("version_details")
	public List<PokemonHeldItemVersion> versionDetails;

}
