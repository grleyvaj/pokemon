package com.pokemon.demo.application.ports.webservice;

import com.pokemon.demo.application.ports.config.TestConfig;
import com.pokemon.demo.application.ports.webservice.resources.get_ability.request.AbilityRequestSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_ability.response.AbilityListResponseSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_ability.response.AbilityResponseSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_base_experience.request.BaseExperienceRequestSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_base_experience.response.BaseExperienceResponseSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_held_item.request.HeldItemsRequestSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_held_item.response.HeldItemListResponseSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_held_item.response.HeldItemResponseSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_held_item.response.HeldItemVersionResponseSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_id.request.IdRequestSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_id.response.IdResponseSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_location_area_encounters.request.LocationAreEncountersRequestSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_location_area_encounters.response.LocalAreaEncountersResponseSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_name.request.NameRequestSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_name.response.NameResponseSoap;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.ws.client.core.WebServiceTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestConfig.class)
class PokemonSoapEndpointIntegrationTest {

	@Autowired
	private WebServiceTemplate webServiceTemplate;

	@LocalServerPort
	private int port;
	private String wsUrl;

	@PostConstruct
	public void init() {
		this.wsUrl = "http://localhost:" + this.port + "/ws";
	}

	@Test
	void when_get_pokemon_id_then_response_is_returned() {
		IdRequestSoap request = new IdRequestSoap();
		request.setName("bulbasaur");
		int expected_id = 1;

		IdResponseSoap response = (IdResponseSoap)this.webServiceTemplate.marshalSendAndReceive(this.wsUrl, request);

		assertNotNull(response);
		assertEquals(response.getId(), expected_id);
	}

	@Test
	void when_get_pokemon_name_then_response_is_returned() {
		NameRequestSoap request = new NameRequestSoap();
		request.setName("bulbasaur");
		String expected_name = "bulbasaur";

		NameResponseSoap response = (NameResponseSoap)
		  this.webServiceTemplate.marshalSendAndReceive(this.wsUrl, request);

		assertNotNull(response);
		assertEquals(response.getName(), expected_name);
	}

	@Test
	void when_get_pokemon_base_experience_then_response_is_returned() {
		BaseExperienceRequestSoap request = new BaseExperienceRequestSoap();
		request.setName("bulbasaur");
		int expected_base_experience = 64;

		BaseExperienceResponseSoap response = (BaseExperienceResponseSoap)
		  this.webServiceTemplate.marshalSendAndReceive(this.wsUrl, request);

		assertNotNull(response);
		assertEquals(response.getBaseExperience(), expected_base_experience);
	}

	@Test
	void when_get_pokemon_location_area_encounters_experience_then_response_is_returned() {
		LocationAreEncountersRequestSoap request = new LocationAreEncountersRequestSoap();
		request.setName("bulbasaur");
		String expected_location_area_encounters = "https://pokeapi.co/api/v2/pokemon/1/encounters";

		LocalAreaEncountersResponseSoap response = (LocalAreaEncountersResponseSoap)
		  this.webServiceTemplate.marshalSendAndReceive(this.wsUrl, request);

		assertNotNull(response);
		assertEquals(response.getLocationAreaEncounters(), expected_location_area_encounters);
	}

	@Test
	void when_get_pokemon_abilities_then_response_is_returned_and_matches_expected() {
		AbilityRequestSoap request = new AbilityRequestSoap().setName("pikachu");
		String url = "http://localhost:" + this.port + "/ws";

		AbilityListResponseSoap response = (AbilityListResponseSoap)
		  this.webServiceTemplate.marshalSendAndReceive(this.wsUrl, request);

		assertNotNull(response, "The response must not be null");
		List<AbilityResponseSoap> abilities = response.getAbilities();
		assertNotNull(abilities, "The list of abilities must not be null");
		assertEquals(2, abilities.size(), "You must return exactly 2 abilities.");

		AbilityResponseSoap a0 = abilities.get(0);
		assertAll(
		  "ability-0",
		  () -> assertFalse(a0.isHidden(), "ability[0].hidden must be false"),
		  () -> assertEquals(1, a0.getSlot(), "ability[0].slot  must be 1"),
		  () -> assertNotNull(a0.getResource(), "ability[0].resource must not be null"),
		  () -> assertEquals("static", a0.getResource().getName(), "ability[0].resource.name"),
		  () -> assertEquals(
			"https://pokeapi.co/api/v2/ability/9/",
			a0.getResource().getUrl(),
			"ability[0].resource.url"
		  )
		);

		AbilityResponseSoap a1 = abilities.get(1);
		assertAll(
		  "ability-1",
		  () -> assertTrue(a1.isHidden(), "ability[1].hidden must be true"),
		  () -> assertEquals(3, a1.getSlot(), "ability[1].slot must be 3"),
		  () -> assertNotNull(a1.getResource(), "ability[1].resource must not be null"),
		  () -> assertEquals("lightning-rod", a1.getResource().getName(), "ability[1].resource.name"),
		  () -> assertEquals(
			"https://pokeapi.co/api/v2/ability/31/",
			a1.getResource().getUrl(),
			"ability[1].resource.url"
		  )
		);
	}

	@Test
	void when_get_pokemon_held_items_then_first_item_matches_expected() {
		HeldItemsRequestSoap request = new HeldItemsRequestSoap().setName("butterfree");
		String url = "http://localhost:" + this.port + "/ws";

		HeldItemListResponseSoap response = (HeldItemListResponseSoap)
		  this.webServiceTemplate.marshalSendAndReceive(this.wsUrl, request);

		assertNotNull(response, "The response must not be null");
		List<HeldItemResponseSoap> heldItems = response.getHeldItems();
		assertNotNull(heldItems, "The list of heldItems must not be null");
		assertFalse(heldItems.isEmpty(), "You must return at least 1 held item");

		HeldItemResponseSoap first = heldItems.getFirst();

		assertAll(
		  "held-item-0",
		  () -> assertNotNull(first.getItem(), "first.item must not be null"),
		  () -> assertEquals("silver-powder", first.getItem().getName(), "first.item.name"),
		  () -> assertEquals("https://pokeapi.co/api/v2/item/199/", first.getItem().getUrl(), "first.item.url"),

		  () -> assertNotNull(first.getVersionDetails(), "first.versionDetails must not be null"),
		  () -> assertFalse(first.getVersionDetails().isEmpty(), "first.versionDetails should not be empty"),
		  () -> {
			  HeldItemVersionResponseSoap v0 = first.getVersionDetails().get(0);
			  assertNotNull(v0.getVersion(), "version[0]must not be nulll");
			  assertEquals("ruby", v0.getVersion().getName(), "version[0].name");
			  assertEquals("https://pokeapi.co/api/v2/version/7/", v0.getVersion().getUrl(), "version[0].url");
			  assertEquals(5, v0.getRarity(), "version[0].rarity");
		  }
		);
	}

}
