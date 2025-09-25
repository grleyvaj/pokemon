package com.pokemon.demo.application.ports._responses.ability;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pokemon.demo.application.ports._responses.resource.ResourceResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AbilityResponse {

	@JsonProperty("is_hidden")
	@Schema(description = "${ability.hidden.description}", example = "false")
	public boolean hidden;

	@JsonProperty("slot")
	@Schema(description = "${ability.slot.description}", example = "1")
	public int slot;

	@JsonProperty("ability")
	@Schema(description = "${ability.name.description}")
	public ResourceResponse ability;

}
