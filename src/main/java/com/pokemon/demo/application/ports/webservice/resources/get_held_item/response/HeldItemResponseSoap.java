package com.pokemon.demo.application.ports.webservice.resources.get_held_item.response;

import com.pokemon.demo.application.ports.webservice.resources.get_ability.response.ResourceResponseSoap;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@XmlRootElement(name = "HeldItemResponseSoap", namespace = "http://pokemon.demo.mx/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"item", "versionDetails"})
public class HeldItemResponseSoap {

	@XmlElement(name = "item", required = true)
	@Schema(
	  description = "${held.item.description}",
	  example = "{\"name\": \"silver-powder\", \"url\": \"https://pokeapi.co/api/v2/item/199/\"}"
	)
	public ResourceResponseSoap item;

	@XmlElement(name = "version_details", required = true)
	@Schema(description = "${held.versions.description}")
	public List<HeldItemVersionResponseSoap> versionDetails;

}