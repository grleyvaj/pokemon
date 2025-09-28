package com.pokemon.demo.application.ports.webservice.resources.get_location_area_encounters.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@XmlRootElement(name = "LocalAreaEncountersResponseSoap", namespace = "http://pokemon.demo.mx/")
@XmlAccessorType(XmlAccessType.FIELD)
public class LocalAreaEncountersResponseSoap {

	@XmlElement(name = "location_area_encounters", required = true)
	@Schema(description = "${pokemon.area.description}", example = "https://pokeapi.co/api/v2/pokemon/12/encounters")
	private String locationAreaEncounters;

}