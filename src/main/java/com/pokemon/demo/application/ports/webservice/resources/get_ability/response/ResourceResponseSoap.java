package com.pokemon.demo.application.ports.webservice.resources.get_ability.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@XmlRootElement(name = "ResourceResponseSoap")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name", "url"})
public class ResourceResponseSoap {

	@Schema(description = "${resource.name.description}", example = "overgrow")
	@XmlElement(required = true)
	private String name;

	@Schema(description = "${resource.url.description}", example = "https://pokeapi.co/api/v2/ability/65/")
	@XmlElement(required = true)
	private String url;

}