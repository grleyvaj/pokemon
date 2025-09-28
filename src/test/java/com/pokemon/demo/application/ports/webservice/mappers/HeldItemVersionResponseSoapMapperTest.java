package com.pokemon.demo.application.ports.webservice.mappers;

import com.pokemon.demo.application.ports.webservice.resources.get_ability.response.ResourceResponseSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_held_item.response.HeldItemVersionResponseSoap;
import com.pokemon.demo.domain.helper.Mapper;
import com.pokemon.demo.domain.model.HeldItemVersion;
import com.pokemon.demo.domain.model.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HeldItemVersionResponseSoapMapperTest {

	@InjectMocks
	private HeldItemVersionResponseSoapMapper mapper;

	@Mock
	private Mapper<Resource, ResourceResponseSoap> resourceResponseSoapMapper;

	@Test
	void test_when_map_with_rarity_and_version_then_maps_all_fields() {
		Resource resource = new Resource("poke", "http://poke");
		ResourceResponseSoap mappedSoap = new ResourceResponseSoap().setName("poke").setUrl("http://poke");

		when(resourceResponseSoapMapper.map(resource)).thenReturn(mappedSoap);

		HeldItemVersion model = mock(HeldItemVersion.class);
		when(model.getRarity()).thenReturn(Optional.of(42));
		when(model.getVersion()).thenReturn(Optional.of(resource));

		HeldItemVersionResponseSoap result = mapper.map(model);

		assertThat(result.getRarity()).isEqualTo(42);
		assertThat(result.getVersion()).isEqualTo(mappedSoap);
		verify(resourceResponseSoapMapper).map(resource);
	}

	@Test
	void test_when_map_with_only_rarity_then_maps_rarity_and_no_version() {
		HeldItemVersion model = mock(HeldItemVersion.class);
		when(model.getRarity()).thenReturn(Optional.of(10));
		when(model.getVersion()).thenReturn(Optional.empty());

		HeldItemVersionResponseSoap result = mapper.map(model);

		assertThat(result.getRarity()).isEqualTo(10);
		assertThat(result.getVersion()).isNull();
		verifyNoInteractions(resourceResponseSoapMapper);
	}

	@Test
	void test_when_map_with_only_version_then_maps_version_and_no_rarity() {
		Resource resource = new Resource("poke", "http://poke");
		ResourceResponseSoap mappedSoap = new ResourceResponseSoap().setName("poke").setUrl("http://poke");
		when(resourceResponseSoapMapper.map(resource)).thenReturn(mappedSoap);
		HeldItemVersion model = mock(HeldItemVersion.class);
		when(model.getRarity()).thenReturn(Optional.empty());
		when(model.getVersion()).thenReturn(Optional.of(resource));

		HeldItemVersionResponseSoap result = mapper.map(model);

		assertThat(result.getVersion()).isEqualTo(mappedSoap);
		verify(resourceResponseSoapMapper).map(resource);
	}

}