package com.pokemon.demo.application.ports.webservice.resources.get_base_experience.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BaseExperienceResponseSoapTest {

	@Test
	void when_object_is_create_then_object_is_built() {
		BaseExperienceResponseSoap current = new BaseExperienceResponseSoap().setBaseExperience(3);

		assertEquals(3, current.getBaseExperience());
	}

}