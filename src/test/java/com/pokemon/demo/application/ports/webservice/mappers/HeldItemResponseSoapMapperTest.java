package com.pokemon.demo.application.ports.webservice.mappers;

import com.pokemon.demo.application.ports.webservice.resources.get_ability.response.ResourceResponseSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_held_item.response.HeldItemResponseSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_held_item.response.HeldItemVersionResponseSoap;
import com.pokemon.demo.domain.helper.ListMapper;
import com.pokemon.demo.domain.helper.Mapper;
import com.pokemon.demo.domain.model.HeldItem;
import com.pokemon.demo.domain.model.HeldItemVersion;
import com.pokemon.demo.domain.model.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HeldItemResponseSoapMapperTest {

	@InjectMocks
	private HeldItemResponseSoapMapper mapper;

	@Mock
	private Mapper<Resource, ResourceResponseSoap> resourceResponseSoapMapper;

	@Mock
	private ListMapper<HeldItemVersion, HeldItemVersionResponseSoap> heldItemVersionResponseSoapListMapper;

	@Test
	void test_when_map_with_item_and_versionDetails_then_maps_all_fields() {
		Resource resource = new Resource("poke", "http://poke");
		ResourceResponseSoap mappedItem = new ResourceResponseSoap().setName("poke").setUrl("http://poke");
		HeldItemVersion version = new HeldItemVersion();
		HeldItemVersionResponseSoap mappedVersion = new HeldItemVersionResponseSoap().setRarity(99);
		when(resourceResponseSoapMapper.map(resource)).thenReturn(mappedItem);
		when(heldItemVersionResponseSoapListMapper.map(List.of(version))).thenReturn(List.of(mappedVersion));

		HeldItem model = mock(HeldItem.class);
		when(model.getItem()).thenReturn(Optional.of(resource));
		when(model.getVersionDetails()).thenReturn(List.of(version));

		HeldItemResponseSoap result = mapper.map(model);

		assertThat(result.getItem()).isEqualTo(mappedItem);
		verify(resourceResponseSoapMapper).map(resource);
		verify(heldItemVersionResponseSoapListMapper).map(List.of(version));
	}

	@Test
	void test_when_map_with_only_item_then_maps_item_and_no_versionDetails() {
		Resource resource = new Resource("poke", "http://poke");
		ResourceResponseSoap mappedItem = new ResourceResponseSoap().setName("poke").setUrl("http://poke");
		when(resourceResponseSoapMapper.map(resource)).thenReturn(mappedItem);
		HeldItem model = mock(HeldItem.class);
		when(model.getItem()).thenReturn(Optional.of(resource));
		when(model.getVersionDetails()).thenReturn(Collections.emptyList());

		HeldItemResponseSoap result = this.mapper.map(model);

		assertThat(result.getItem()).isEqualTo(mappedItem);
		verify(resourceResponseSoapMapper).map(resource);
		verifyNoInteractions(heldItemVersionResponseSoapListMapper);
	}

	@Test
	void test_when_map_with_only_versionDetails_then_maps_versions_and_no_item() {
		HeldItemVersion version = new HeldItemVersion();
		HeldItemVersionResponseSoap mappedVersion = new HeldItemVersionResponseSoap().setRarity(88);
		when(heldItemVersionResponseSoapListMapper.map(List.of(version))).thenReturn(List.of(mappedVersion));
		HeldItem model = mock(HeldItem.class);
		when(model.getItem()).thenReturn(Optional.empty());
		when(model.getVersionDetails()).thenReturn(List.of(version));

		HeldItemResponseSoap result = this.mapper.map(model);

		assertThat(result.getItem()).isNull();
		verify(heldItemVersionResponseSoapListMapper).map(List.of(version));
		verifyNoInteractions(resourceResponseSoapMapper);
	}

	@Test
	void test_when_map_with_empty_model_then_returns_empty_response() {
		HeldItem model = mock(HeldItem.class);
		when(model.getItem()).thenReturn(Optional.empty());
		when(model.getVersionDetails()).thenReturn(Collections.emptyList());

		HeldItemResponseSoap result = this.mapper.map(model);

		assertThat(result.getItem()).isNull();
		verifyNoInteractions(resourceResponseSoapMapper, heldItemVersionResponseSoapListMapper);
	}

}