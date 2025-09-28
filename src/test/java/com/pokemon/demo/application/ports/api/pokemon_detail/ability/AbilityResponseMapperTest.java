package com.pokemon.demo.application.ports.api.pokemon_detail.ability;

import com.pokemon.demo.application.ports.api.pokemon_detail.resource.ResourceResponse;
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
class AbilityResponseMapperTest {

	@InjectMocks
	private AbilityResponseMapper mapper;

	@Mock
	private Mapper<Resource, ResourceResponse> resourceResponseMapper;

	@Test
	void test_when_map_valid_ability_with_resource_then_return_expected_response() {
		Resource resource = new Resource("static", "https://pokeapi.co/api/v2/ability/9/");
		Ability ability = new Ability(true, 3).setResource(resource);
		ResourceResponse mockedResourceResponse = new ResourceResponse()
		  .setName("static")
		  .setUrl("https://pokeapi.co/api/v2/ability/9/");
		when(this.resourceResponseMapper.map(resource)).thenReturn(mockedResourceResponse);

		AbilityResponse response = this.mapper.map(ability);

		assertNotNull(response, "Response must not be null");
		assertTrue(response.isHidden(), "Hidden must be true");
		assertEquals(3, response.getSlot(), "Slot must match input");
		assertNotNull(response.getAbility(), "Resource must not be null");
		assertEquals("static", response.getAbility().getName());
		assertEquals("https://pokeapi.co/api/v2/ability/9/", response.getAbility().getUrl());

		verify(this.resourceResponseMapper).map(resource);
	}

	@Test
	void test_when_map_valid_ability_without_resource_then_return_response_with_null_resource() {
		Ability ability = new Ability(false, 1);

		AbilityResponse response = this.mapper.map(ability);

		assertNotNull(response, "Response must not be null");
		assertFalse(response.isHidden(), "Hidden must be false");
		assertEquals(1, response.getSlot(), "Slot must match input");
		assertNull(response.getAbility(), "Resource must be null when Ability has no resource");

		verifyNoInteractions(this.resourceResponseMapper);
	}

}