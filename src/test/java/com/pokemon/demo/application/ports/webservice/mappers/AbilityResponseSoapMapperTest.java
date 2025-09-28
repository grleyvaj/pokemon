package com.pokemon.demo.application.ports.webservice.mappers;

import com.pokemon.demo.application.ports.webservice.resources.get_ability.response.AbilityResponseSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_ability.response.ResourceResponseSoap;
import com.pokemon.demo.domain.helper.Mapper;
import com.pokemon.demo.domain.model.Ability;
import com.pokemon.demo.domain.model.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AbilityResponseSoapMapperTest {

	@InjectMocks
	private AbilityResponseSoapMapper mapper;

	@Mock
	private Mapper<Resource, ResourceResponseSoap> resourceResponseSoapMapper;

	@Test
	void test_when_map_valid_ability_with_resource_then_return_expected_response() {
		Resource resource = new Resource("static", "https://pokeapi.co/api/v2/ability/9/");
		Ability ability = new Ability(true, 3).setResource(resource);

		ResourceResponseSoap mockedResourceResponse = new ResourceResponseSoap()
		  .setName("static")
		  .setUrl("https://pokeapi.co/api/v2/ability/9/");

		when(resourceResponseSoapMapper.map(resource)).thenReturn(mockedResourceResponse);

		AbilityResponseSoap response = mapper.map(ability);

		assertNotNull(response, "Response must not be null");
		assertTrue(response.isHidden(), "Hidden must be true");
		assertEquals(3, response.getSlot(), "Slot must match input");
		assertNotNull(response.getResource(), "Resource must not be null");
		assertEquals("static", response.getResource().getName());
		assertEquals("https://pokeapi.co/api/v2/ability/9/", response.getResource().getUrl());

		verify(resourceResponseSoapMapper).map(resource);
	}

	@Test
	void test_when_map_valid_ability_without_resource_then_return_response_with_null_resource() {
		Ability ability = new Ability(false, 1);

		AbilityResponseSoap response = mapper.map(ability);

		assertNotNull(response, "Response must not be null");
		assertFalse(response.isHidden(), "Hidden must be false");
		assertEquals(1, response.getSlot(), "Slot must match input");
		assertNull(response.getResource(), "Resource must be null when Ability has no resource");

		verifyNoInteractions(resourceResponseSoapMapper);
	}

}