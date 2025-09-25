package com.pokemon.demo.infrastructure.api.dtos.pokemon;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pokemon.demo.infrastructure.api.dtos.ability.PokemonAbility;
import com.pokemon.demo.infrastructure.api.dtos.held.PokemonHeldItem;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonResponse {

	@JsonProperty("id")
	private int id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("base_experience")
	private int baseExperience;

	@JsonProperty("location_area_encounters")
	private String locationAreaEncounters;

	@JsonProperty("abilities")
	private List<PokemonAbility> abilities;

	@JsonProperty("held_items")
	private List<PokemonHeldItem> heldItems;

}
