package com.pokemon.demo.application.ports.webservice.resources.get_ability.response;

import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@XmlRootElement(name = "AbilityResponseSoap", namespace = "http://pokemon.demo.mx/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"hidden", "slot", "resource"})
public class AbilityResponseSoap {

	@XmlElement(name = "hidden", required = true)
	private boolean hidden;

	@XmlElement(name = "slot", required = true)
	private int slot;

	@XmlElement(name = "resource", required = true)
	private ResourceResponseSoap resource;

}
