package com.pokemon.demo.application.ports.webservice.resources.get_location_area_encounters.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocationAreEncountersRequestSoapTest {

	@Test
	void when_object_is_create_then_object_is_built() {
		LocationAreEncountersRequestSoap request = new LocationAreEncountersRequestSoap().setName("::name::");

		assertEquals("::name::", request.getName());
	}


}