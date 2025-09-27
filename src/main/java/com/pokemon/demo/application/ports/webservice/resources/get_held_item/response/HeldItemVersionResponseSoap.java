package com.pokemon.demo.application.ports.webservice.resources.get_held_item.response;

import com.pokemon.demo.application.ports.webservice.resources.get_ability.response.ResourceResponseSoap;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;

@Data
@Getter
@Accessors(chain = true)
@XmlRootElement(name = "HeldItemVersionResponseSoap", namespace = "http://pokemon.demo.mx/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"version", "rarity"})
public class HeldItemVersionResponseSoap {

	@XmlElement(name = "version", required = true)
	@Schema(
	  description = "${held.version.description}",
	  example = "{\"name\": \"ruby\", \"url\": \"https://pokeapi.co/api/v2/version/7/\"}"
	)
	private ResourceResponseSoap version;

	@XmlElement(name = "rarity", required = true)
	@Schema(description = "${held.rarity.description}", example = "5")
	private int rarity;

}
