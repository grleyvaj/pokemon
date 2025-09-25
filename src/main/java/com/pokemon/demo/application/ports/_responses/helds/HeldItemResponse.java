package com.pokemon.demo.application.ports._responses.helds;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pokemon.demo.application.ports._responses.resource.ResourceResponse;
import com.pokemon.demo.domain.model.Resource;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class HeldItemResponse {

	@JsonProperty("item")
	@Schema(
	  description = "${held.item.description}",
	  example = "{\"name\": \"silver-powder\", \"url\": \"https://pokeapi.co/api/v2/item/199/\"}"
	)
	public ResourceResponse item;

	@JsonProperty("version_details")
	@Schema(description = "${held.versions.description}")
	public List<HeldItemVersionResponse> versionDetails;

}
