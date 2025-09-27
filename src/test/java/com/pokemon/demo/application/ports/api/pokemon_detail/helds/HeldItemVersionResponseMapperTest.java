package com.pokemon.demo.application.ports.api.pokemon_detail.helds;

import com.pokemon.demo.builder.ResourceBuilder;
import com.pokemon.demo.domain.model.HeldItemVersion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class HeldItemVersionResponseMapperTest {

	@InjectMocks
	private HeldItemVersionResponseMapper mapper;

	@Test
	void test_when_map_with_rarity_and_version_then_maps_all_fields() {
		HeldItemVersionResponse result = this.mapper.map(
		  new HeldItemVersion()
			.setVersion(new ResourceBuilder().build(2))
			.setRarity(42)
		);

		assertThat(result.getRarity()).isEqualTo(42);
		assertThat(result.getVersion()).isEqualTo(new ResourceBuilder().build(2));
	}

	@Test
	void test_when_map_without_rarity_and_version_then_maps_all_fields() {
		HeldItemVersionResponse result = this.mapper.map(
		  new HeldItemVersion()
		);

		assertEquals(0, result.getRarity());
		assertNull(result.getVersion());
	}

}