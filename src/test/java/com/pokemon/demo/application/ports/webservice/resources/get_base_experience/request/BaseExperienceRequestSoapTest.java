package com.pokemon.demo.application.ports.webservice.resources.get_base_experience.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BaseExperienceRequestSoapTest {

	@Test
	void when_object_is_create_then_object_is_built() {
		BaseExperienceRequestSoap request = new BaseExperienceRequestSoap().setName("::name::");

		assertEquals("::name::", request.getName());
	}

}