package com.pokemon.demo.application.ports.api.pokemon_detail.helds;

import com.pokemon.demo.application.ports.api.pokemon_detail.resource.ResourceResponse;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HeldItemResponseMapperTest {

	@InjectMocks
	private HeldItemResponseMapper mapper;

	@Mock
	private Mapper<Resource, ResourceResponse> resourceResponseMapper;

	@Mock
	private ListMapper<HeldItemVersion, HeldItemVersionResponse> heldItemVersionResponseListMapper;

	@Test
	void test_when_map_with_item_and_versionDetails_then_maps_all_fields() {
		Resource resource = new Resource("poke", "http://poke");
		ResourceResponse mappedItem = new ResourceResponse().setName("poke").setUrl("http://poke");

		HeldItemVersion version = new HeldItemVersion();
		HeldItemVersionResponse mappedVersion = new HeldItemVersionResponse().setRarity(99);
		when(this.resourceResponseMapper.map(resource)).thenReturn(mappedItem);
		when(this.heldItemVersionResponseListMapper.map(List.of(version))).thenReturn(List.of(mappedVersion));
		HeldItem model = mock(HeldItem.class);
		when(model.getItem()).thenReturn(Optional.of(resource));
		when(model.getVersionDetails()).thenReturn(List.of(version));

		HeldItemResponse result = this.mapper.map(model);

		assertThat(result.getItem()).isEqualTo(mappedItem);
		assertThat(result.getVersionDetails()).containsExactly(mappedVersion);
		verify(this.resourceResponseMapper).map(resource);
		verify(this.heldItemVersionResponseListMapper).map(List.of(version));
	}

	@Test
	void test_when_map_with_only_item_then_maps_item_and_no_versionDetails() {
		Resource resource = new Resource("poke", "http://poke");
		ResourceResponse mappedItem = new ResourceResponse().setName("poke").setUrl("http://poke");
		when(this.resourceResponseMapper.map(resource)).thenReturn(mappedItem);
		HeldItem model = mock(HeldItem.class);
		when(model.getItem()).thenReturn(Optional.of(resource));
		when(model.getVersionDetails()).thenReturn(Collections.emptyList());

		HeldItemResponse result = this.mapper.map(model);

		assertThat(result.getItem()).isEqualTo(mappedItem);
		assertThat(result.getVersionDetails()).isNullOrEmpty();
		verify(this.resourceResponseMapper).map(resource);
		verifyNoInteractions(this.heldItemVersionResponseListMapper);
	}

	@Test
	void test_when_map_with_only_versionDetails_then_maps_versions_and_no_item() {
		HeldItemVersion version = new HeldItemVersion();
		HeldItemVersionResponse mappedVersion = new HeldItemVersionResponse().setRarity(88);
		when(this.heldItemVersionResponseListMapper.map(List.of(version))).thenReturn(List.of(mappedVersion));
		HeldItem model = mock(HeldItem.class);
		when(model.getItem()).thenReturn(Optional.empty());
		when(model.getVersionDetails()).thenReturn(List.of(version));

		HeldItemResponse result = mapper.map(model);

		assertThat(result.getItem()).isNull();
		assertThat(result.getVersionDetails()).containsExactly(mappedVersion);
		verify(this.heldItemVersionResponseListMapper).map(List.of(version));
		verifyNoInteractions(this.resourceResponseMapper);
	}

	@Test
	void test_when_map_with_empty_model_then_returns_empty_response() {
		HeldItem model = mock(HeldItem.class);
		when(model.getItem()).thenReturn(Optional.empty());
		when(model.getVersionDetails()).thenReturn(Collections.emptyList());

		HeldItemResponse result = this.mapper.map(model);

		assertThat(result.getItem()).isNull();
		assertThat(result.getVersionDetails()).isNullOrEmpty();
		verifyNoInteractions(this.resourceResponseMapper, this.heldItemVersionResponseListMapper);
	}

}
