package com.pokemon.demo.infrastructure.api.dtos.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NamedAPIResource {

	@JsonProperty("name")
	public String name;

	@JsonProperty("url")
	public String url;

}
