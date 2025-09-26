package com.pokemon.demo.application.ports.webservice.get_name.request;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "NameRequestSoap", namespace = "http://pokemon.demo.mx/")
@XmlAccessorType(XmlAccessType.FIELD)
public class NameRequestSoap {

	@XmlElement(name = "name", required = true, namespace = "http://pokemon.demo.mx/")
	private String name;

}