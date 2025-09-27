package com.pokemon.demo.application.ports.webservice.resources.get_id.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@XmlRootElement(name = "IdRequestSoap", namespace = "http://pokemon.demo.mx/")
@XmlAccessorType(XmlAccessType.FIELD)
public class IdRequestSoap {

	@XmlElement(name = "name", required = true, namespace = "http://pokemon.demo.mx/")
	@Schema(description = "${pokemon.name.description}", example = "butterfree")
	private String name;

}