package com.pokemon.demo.application.ports.webservice.resources.get_ability.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@XmlRootElement(name = "AbilityListResponseSoap", namespace = "http://pokemon.demo.mx/")
@XmlAccessorType(XmlAccessType.FIELD)
public class AbilityListResponseSoap {

	@XmlElement(name = "ability", required = true)
	private List<AbilityResponseSoap> abilities;

}