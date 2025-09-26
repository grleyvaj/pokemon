package com.pokemon.demo.application.ports.webservice.get_ability.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@XmlRootElement(name = "AbilityResponseSoap", namespace = "http://pokemon.demo.mx/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"hidden", "slot", "ability"})
public class AbilityResponseSoap {

	@XmlElement(name = "hidden", required = true)
	@Schema(description = "${ability.hidden.description}", example = "false")
	private boolean hidden;

	@XmlElement(name = "slot", required = true)
	@Schema(description = "${ability.slot.description}", example = "1")
	private int slot;

	@XmlElement(name = "ability", required = true)
	@Schema(description = "${ability.name.description}")
	private ResourceResponseSoap ability;

}