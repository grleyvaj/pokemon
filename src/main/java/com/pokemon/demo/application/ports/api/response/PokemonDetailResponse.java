package com.pokemon.demo.application.ports.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pokemon.demo.application.ports._responses.ability.AbilityResponse;
import com.pokemon.demo.application.ports._responses.helds.HeldItemResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class PokemonDetailResponse {

	@JsonProperty("abilities")
	@Schema(description = "${pokemon.abilities.description}")
	private List<AbilityResponse> abilities;

	@JsonProperty("base_experience")
	@Schema(description = "${pokemon.experience.description}", example = "178")
	private int baseExperience;

	@JsonProperty("held_items")
	@Schema(description = "${pokemon.held.description}")
	private List<HeldItemResponse> heldItems;

	@JsonProperty("id")
	@Schema(description = "${pokemon.id.description}", example = "12")
	private int id;

	@JsonProperty("name")
	@Schema(description = "${pokemon.name.description}", example = "butterfree")
	private String name;

	@JsonProperty("location_area_encounters")
	@Schema(description = "${pokemon.area.description}", example = "https://pokeapi.co/api/v2/pokemon/12/encounters")
	private String locationAreaEncounters;

}
