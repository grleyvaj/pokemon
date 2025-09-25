package com.pokemon.demo.application.ports._responses.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResourceResponse {

	@JsonProperty("name")
	@Schema(description = "${resource.name.description}", example = "overgrow")
	public String name;

	@JsonProperty("url")
	@Schema(description = "${resource.url.description}", example = "https://pokeapi.co/api/v2/ability/65/")
	public String url;

}
