package com.pokemon.demo.application.ports.webservice.resources.get_location_area_encounters.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocalAreaEncountersResponseSoapTest {

	@Test
	void when_object_is_create_then_object_is_built() {
		LocalAreaEncountersResponseSoap current = new LocalAreaEncountersResponseSoap()
		  .setLocationAreaEncounters("::location::");

		assertEquals("::location::", current.getLocationAreaEncounters());
	}

}