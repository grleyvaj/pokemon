package com.pokemon.demo.application.ports.webservice.resources.get_base_experience.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "BaseExperienceRequestSoap", namespace = "http://pokemon.demo.mx/")
@XmlAccessorType(XmlAccessType.FIELD)
public class BaseExperienceRequestSoap {

	@XmlElement(name = "name", required = true, namespace = "http://pokemon.demo.mx/")
	@Schema(description = "${pokemon.name.description}", example = "butterfree")
	private String name;

}