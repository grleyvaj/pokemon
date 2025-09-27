package com.pokemon.demo.application.ports.webservice.mappers;

import com.pokemon.demo.application.ports.webservice.resources.get_ability.response.ResourceResponseSoap;
import com.pokemon.demo.domain.model.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ResourceResponseSoapMapperTest {

	@InjectMocks
	private ResourceResponseSoapMapper mapper;

	@Test
	void test_when_map_valid_resource_then_return_expected_response() {
		Resource resource = new Resource(
		  "pikachu",
		  "https://pokeapi.co/api/v2/pokemon/25/"
		);

		ResourceResponseSoap response = this.mapper.map(resource);

		assertNotNull(response, "Response must not be null");
		assertEquals("pikachu", response.getName());
		assertEquals("https://pokeapi.co/api/v2/pokemon/25/", response.getUrl());
	}

}