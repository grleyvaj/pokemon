package com.pokemon.demo.application.ports.webservice;

import com.pokemon.demo.application.ports.webservice.resources.get_ability.request.AbilityRequestSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_ability.response.AbilityListResponseSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_ability.response.AbilityResponseSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_base_experience.request.BaseExperienceRequestSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_base_experience.response.BaseExperienceResponseSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_held_item.request.HeldItemsRequestSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_held_item.response.HeldItemListResponseSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_held_item.response.HeldItemResponseSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_id.request.IdRequestSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_id.response.IdResponseSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_location_area_encounters.request.LocationAreEncountersRequestSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_location_area_encounters.response.LocalAreaEncountersResponseSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_name.request.NameRequestSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_name.response.NameResponseSoap;
import com.pokemon.demo.builder.AbilityBuilder;
import com.pokemon.demo.builder.AbilityResponseSoapBuilder;
import com.pokemon.demo.builder.HeldItemBuilder;
import com.pokemon.demo.builder.HeldItemResponseSoapBuilder;
import com.pokemon.demo.domain.helper.Mapper;
import com.pokemon.demo.domain.model.Ability;
import com.pokemon.demo.domain.model.HeldItem;
import com.pokemon.demo.domain.model.Method;
import com.pokemon.demo.domain.repository.RequestLogCreateInput;
import com.pokemon.demo.domain.use_case.pokemon.get_abilities.PokemonGetAbilitiesUseCase;
import com.pokemon.demo.domain.use_case.pokemon.get_base_experience.PokemonGetBaseExperienceUseCase;
import com.pokemon.demo.domain.use_case.pokemon.get_held_items.PokemonGetHeldItemsUseCase;
import com.pokemon.demo.domain.use_case.pokemon.get_id.PokemonGetIdUseCase;
import com.pokemon.demo.domain.use_case.pokemon.get_location_area.PokemonGetLocationAreaEncounterUseCase;
import com.pokemon.demo.domain.use_case.pokemon.get_name.PokemonGetNameUseCase;
import com.pokemon.demo.domain.use_case.request_log.create.RequestLogCreateUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PokemonSoapEndpointTest {

	private PokemonSoapEndpoint endpoint;

	@Mock
	private PokemonGetIdUseCase pokemonGetIdUseCase;

	@Mock
	private PokemonGetNameUseCase pokemonGetNameUseCase;

	@Mock
	private PokemonGetBaseExperienceUseCase pokemonGetBaseExperienceUseCase;

	@Mock
	private PokemonGetLocationAreaEncounterUseCase pokemonGetLocationAreaEncounterUseCase;

	@Mock
	private PokemonGetAbilitiesUseCase pokemonGetAbilitiesUseCase;

	@Mock
	private PokemonGetHeldItemsUseCase pokemonGetHeldItemsUseCase;

	@Mock
	private Mapper<Ability, AbilityResponseSoap> abilityResponseSoapMapper;

	@Mock
	private Mapper<HeldItem, HeldItemResponseSoap> heldItemResponseSoapMapper;

	@Mock
	private RequestLogCreateUseCase requestLogCreateUseCase;

	@BeforeEach
	void setUp() {
		this.endpoint = new PokemonSoapEndpoint(
		  this.pokemonGetIdUseCase,
		  this.pokemonGetNameUseCase,
		  this.pokemonGetBaseExperienceUseCase,
		  this.pokemonGetLocationAreaEncounterUseCase,
		  this.pokemonGetAbilitiesUseCase,
		  this.pokemonGetHeldItemsUseCase,
		  this.requestLogCreateUseCase,
		  this.abilityResponseSoapMapper,
		  this.heldItemResponseSoapMapper
		);
	}

	@Test
	void when_get_pokemon_id_is_called_then_use_case_is_used() {
		String name = "::name";
		IdRequestSoap request = new IdRequestSoap().setName(name);

		this.endpoint.getId(request);

		verify(this.pokemonGetIdUseCase).execute(name);
	}

	@Test
	void when_get_pokemon_id_is_called_and_use_case_is_invoked_then_id_is_retrieve() {
		int expected_id = 5;
		when(this.pokemonGetIdUseCase.execute(anyString())).thenReturn(expected_id);

		IdResponseSoap response = this.endpoint.getId(new IdRequestSoap().setName("::name"));

		assertEquals(response.getId(), expected_id);
	}

	@Test
	void when_get_pokemon_id_is_called_then_request_log_is_saved() {
		String name = "::name";
		IdRequestSoap request = new IdRequestSoap().setName(name);
		when(this.pokemonGetIdUseCase.execute(anyString())).thenReturn(5);

		IdResponseSoap response = this.endpoint.getId(request);

		ArgumentCaptor<RequestLogCreateInput> captor = ArgumentCaptor.forClass(RequestLogCreateInput.class);
		verify(this.requestLogCreateUseCase).execute(captor.capture());
		RequestLogCreateInput log = captor.getValue();
		assertEquals(Method.ID, log.getMethod());
		assertEquals(request, log.getRequestObj());
		assertEquals(response, log.getResponseObj());
		assertNotNull(log.getDurationMs());
		assertNotNull(log.getOriginIp());
	}

	@Test
	void when_get_pokemon_name_is_called_then_use_case_is_used() {
		String name = "::name";
		NameRequestSoap request = new NameRequestSoap().setName(name);

		this.endpoint.getName(request);

		verify(this.pokemonGetNameUseCase).execute(name);
	}

	@Test
	void when_get_pokemon_name_is_called_and_use_case_is_invoked_then_id_is_retrieve() {
		String expected_name = "::name::";
		when(this.pokemonGetNameUseCase.execute(anyString())).thenReturn(expected_name);

		NameResponseSoap response = this.endpoint.getName(new NameRequestSoap().setName("::name"));

		assertEquals(response.getName(), expected_name);
	}

	@Test
	void when_get_pokemon_name_is_called_then_request_log_is_saved() {
		String name = "::name";
		NameRequestSoap request = new NameRequestSoap().setName(name);
		when(this.pokemonGetNameUseCase.execute(anyString())).thenReturn(name);

		NameResponseSoap response = this.endpoint.getName(new NameRequestSoap().setName("::name"));

		ArgumentCaptor<RequestLogCreateInput> captor = ArgumentCaptor.forClass(RequestLogCreateInput.class);
		verify(this.requestLogCreateUseCase).execute(captor.capture());
		RequestLogCreateInput log = captor.getValue();
		assertEquals(Method.NAME, log.getMethod());
		assertEquals(request, log.getRequestObj());
		assertEquals(response, log.getResponseObj());
		assertNotNull(log.getDurationMs());
		assertNotNull(log.getOriginIp());
	}

	@Test
	void when_get_pokemon_base_experience_is_called_then_use_case_is_used() {
		String name = "::name";
		BaseExperienceRequestSoap request = new BaseExperienceRequestSoap().setName(name);

		this.endpoint.getBaseExperience(request);

		verify(this.pokemonGetBaseExperienceUseCase).execute(name);
	}

	@Test
	void when_get_pokemon_base_experience_is_called_and_use_case_is_invoked_then_id_is_retrieve() {
		int expected_base_experience = 3;
		when(this.pokemonGetBaseExperienceUseCase.execute(anyString())).thenReturn(expected_base_experience);

		BaseExperienceResponseSoap response = this.endpoint.getBaseExperience(
		  new BaseExperienceRequestSoap().setName("::name")
		);

		assertEquals(response.getBaseExperience(), expected_base_experience);
	}

	@Test
	void when_get_pokemon_base_experience_is_called_then_request_log_is_saved() {
		String name = "::name";
		int base_experience = 3;
		BaseExperienceRequestSoap request = new BaseExperienceRequestSoap().setName(name);
		when(this.pokemonGetBaseExperienceUseCase.execute(anyString())).thenReturn(base_experience);

		BaseExperienceResponseSoap response = this.endpoint.getBaseExperience(
		  new BaseExperienceRequestSoap().setName("::name")
		);

		ArgumentCaptor<RequestLogCreateInput> captor = ArgumentCaptor.forClass(RequestLogCreateInput.class);
		verify(this.requestLogCreateUseCase).execute(captor.capture());
		RequestLogCreateInput log = captor.getValue();
		assertEquals(Method.BASE_EXPERIENCE, log.getMethod());
		assertEquals(request, log.getRequestObj());
		assertEquals(response, log.getResponseObj());
		assertNotNull(log.getDurationMs());
		assertNotNull(log.getOriginIp());
	}

	@Test
	void when_get_pokemon_location_area_encounters_is_called_then_use_case_is_used() {
		String name = "::name";
		LocationAreEncountersRequestSoap request = new LocationAreEncountersRequestSoap().setName(name);

		this.endpoint.getLocalAreaEncounter(request);

		verify(this.pokemonGetLocationAreaEncounterUseCase).execute(name);
	}

	@Test
	void when_get_pokemon_location_area_encounters_is_called_and_use_case_is_invoked_then_id_is_retrieve() {
		String expected_location = "::expected_location::";
		when(this.pokemonGetLocationAreaEncounterUseCase.execute(anyString())).thenReturn(expected_location);

		LocalAreaEncountersResponseSoap response = this.endpoint.getLocalAreaEncounter(
		  new LocationAreEncountersRequestSoap().setName("::name")
		);

		assertEquals(response.getLocationAreaEncounters(), expected_location);
	}

	@Test
	void when_get_pokemon_location_area_encounters_is_called_then_request_log_is_saved() {
		String expected_location = "::expected_location::";
		LocationAreEncountersRequestSoap request = new LocationAreEncountersRequestSoap().setName("::name");
		when(this.pokemonGetLocationAreaEncounterUseCase.execute(anyString())).thenReturn(expected_location);

		LocalAreaEncountersResponseSoap response = this.endpoint.getLocalAreaEncounter(
		  new LocationAreEncountersRequestSoap().setName("::name")
		);

		ArgumentCaptor<RequestLogCreateInput> captor = ArgumentCaptor.forClass(RequestLogCreateInput.class);
		verify(this.requestLogCreateUseCase).execute(captor.capture());
		RequestLogCreateInput log = captor.getValue();
		assertEquals(Method.LOCATION_AREA_ENCOUNTERS, log.getMethod());
		assertEquals(request, log.getRequestObj());
		assertEquals(response, log.getResponseObj());
		assertNotNull(log.getDurationMs());
		assertNotNull(log.getOriginIp());
	}

	@Test
	void when_get_abilities_is_called_then_use_case_is_used() {
		String name = "::name";
		AbilityRequestSoap request = new AbilityRequestSoap().setName(name);

		this.endpoint.getAbilities(request);

		verify(this.pokemonGetAbilitiesUseCase).execute(name);
	}

	@Test
	void when_get_abilities_is_called_and_use_case_is_invoked_then_abilities_are_mapped() {
		when(this.pokemonGetAbilitiesUseCase.execute(anyString()))
		  .thenReturn(List.of(new AbilityBuilder().build(3)));

		this.endpoint.getAbilities(new AbilityRequestSoap().setName("::name::"));

		verify(this.abilityResponseSoapMapper).map(new AbilityBuilder().build(3));
	}

	@Test
	void when_get_abilities_is_called_and_data_is_mapped_then_abilities_are_retrieved() {
		when(this.pokemonGetAbilitiesUseCase.execute(anyString()))
		  .thenReturn(List.of(new AbilityBuilder().build(3)));
		when(this.abilityResponseSoapMapper.map(any()))
		  .thenReturn(new AbilityResponseSoapBuilder().build(3));

		AbilityListResponseSoap response = this.endpoint.getAbilities(new AbilityRequestSoap().setName("::name::"));

		assertEquals(
		  response,
		  new AbilityListResponseSoap().setAbilities(List.of(new AbilityResponseSoapBuilder().build(3)))
		);
	}

	@Test
	void when_get_abilities_is_called_and_data_is_mapped_then_request_log_is_saved() {
		AbilityRequestSoap request = new AbilityRequestSoap().setName("::name::");
		when(this.pokemonGetAbilitiesUseCase.execute(anyString()))
		  .thenReturn(List.of(new AbilityBuilder().build(3)));
		when(this.abilityResponseSoapMapper.map(any()))
		  .thenReturn(new AbilityResponseSoapBuilder().build(3));

		AbilityListResponseSoap response = this.endpoint.getAbilities(request);

		ArgumentCaptor<RequestLogCreateInput> captor = ArgumentCaptor.forClass(RequestLogCreateInput.class);
		verify(this.requestLogCreateUseCase).execute(captor.capture());
		RequestLogCreateInput log = captor.getValue();
		assertEquals(Method.ABILITIES, log.getMethod());
		assertEquals(request, log.getRequestObj());
		assertEquals(response, log.getResponseObj());
		assertNotNull(log.getDurationMs());
		assertNotNull(log.getOriginIp());
	}

	@Test
	void when_get_held_items_is_called_then_use_case_is_used() {
		String name = "::name";
		HeldItemsRequestSoap request = new HeldItemsRequestSoap().setName(name);

		this.endpoint.getHeldItems(request);

		verify(this.pokemonGetHeldItemsUseCase).execute(name);
	}

	@Test
	void when_get_held_items_is_called_and_use_case_is_invoked_then_items_are_mapped() {
		when(this.pokemonGetHeldItemsUseCase.execute(anyString()))
		  .thenReturn(List.of(new HeldItemBuilder().build(3)));

		this.endpoint.getHeldItems(new HeldItemsRequestSoap().setName("::name::"));

		verify(this.heldItemResponseSoapMapper).map(new HeldItemBuilder().build(3));
	}

	@Test
	void when_get_held_items_is_called_and_data_is_mapped_then_abilities_are_retrieved() {
		when(this.pokemonGetHeldItemsUseCase.execute(anyString()))
		  .thenReturn(List.of(new HeldItemBuilder().build(3)));
		when(this.heldItemResponseSoapMapper.map(any()))
		  .thenReturn(new HeldItemResponseSoapBuilder().build(2));

		HeldItemListResponseSoap response = this.endpoint.getHeldItems(new HeldItemsRequestSoap().setName("::name::"));

		assertEquals(
		  response,
		  new HeldItemListResponseSoap().setHeldItems(List.of(new HeldItemResponseSoapBuilder().build(2)))
		);
	}

	@Test
	void when_get_held_items_is_called_and_data_is_mapped_then_request_log_is_saved() {
		HeldItemsRequestSoap request = new HeldItemsRequestSoap().setName("::name::");
		when(this.pokemonGetHeldItemsUseCase.execute(anyString()))
		  .thenReturn(List.of(new HeldItemBuilder().build(3)));
		when(this.heldItemResponseSoapMapper.map(any()))
		  .thenReturn(new HeldItemResponseSoapBuilder().build(2));

		HeldItemListResponseSoap response = this.endpoint.getHeldItems(request);

		ArgumentCaptor<RequestLogCreateInput> captor = ArgumentCaptor.forClass(RequestLogCreateInput.class);
		verify(this.requestLogCreateUseCase).execute(captor.capture());
		RequestLogCreateInput log = captor.getValue();
		assertEquals(Method.HELD_ITEMS, log.getMethod());
		assertEquals(request, log.getRequestObj());
		assertEquals(response, log.getResponseObj());
		assertNotNull(log.getDurationMs());
		assertNotNull(log.getOriginIp());
	}

}