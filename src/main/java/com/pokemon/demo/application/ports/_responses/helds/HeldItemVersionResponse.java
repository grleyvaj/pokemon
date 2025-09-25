package com.pokemon.demo.application.ports._responses.helds;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pokemon.demo.domain.model.Resource;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class HeldItemVersionResponse {

	@JsonProperty("version")
	@Schema(
	  description = "${held.version.description}",
	  example = "{\"name\": \"ruby\", \"url\": \"https://pokeapi.co/api/v2/version/7/\"}"
	)
	private Resource version;

	@JsonProperty("rarity")
	@Schema(description = "${held.rarity.description}", example = "5")
	private int rarity;

}
