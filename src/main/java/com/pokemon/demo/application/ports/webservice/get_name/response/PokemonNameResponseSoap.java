package com.pokemon.demo.application.ports.webservice.get_name.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@XmlRootElement(name = "PokemonNameResponseSoap", namespace = "http://pokemon.demo.mx/")
@XmlAccessorType(XmlAccessType.FIELD)
public class PokemonNameResponseSoap {

	@XmlElement(name = "name", required = true)
	@Schema(description = "${pokemon.name.description}", example = "ivysaur")
	private String name;

}