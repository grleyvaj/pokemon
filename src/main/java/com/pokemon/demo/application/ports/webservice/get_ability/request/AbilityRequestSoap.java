package com.pokemon.demo.application.ports.webservice.get_ability.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlRootElement(name = "AbilityRequestSoap", namespace = "http://pokemon.demo.mx/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name"})
public class AbilityRequestSoap {

	@XmlElement(name = "name", required = true)
	@Schema(description = "${pokemon.name.description}", example = "butterfree")
	private String name;

	public AbilityRequestSoap() {
	}

}