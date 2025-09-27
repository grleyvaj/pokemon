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
import com.pokemon.demo.domain.exception.PokemonClientApiException;
import com.pokemon.demo.domain.exception.ResourceNotFoundException;
import com.pokemon.demo.domain.helper.Mapper;
import com.pokemon.demo.domain.helper.OriginIpExtractor;
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
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@RequiredArgsConstructor
@Endpoint
public class PokemonSoapEndpoint {

	private final PokemonGetIdUseCase pokemonGetIdUseCase;
	private final PokemonGetNameUseCase pokemonGetNameUseCase;
	private final PokemonGetBaseExperienceUseCase pokemonGetBaseExperienceUseCase;
	private final PokemonGetLocationAreaEncounterUseCase pokemonGetLocationAreaEncounterUseCase;
	private final PokemonGetAbilitiesUseCase pokemonGetAbilitiesUseCase;
	private final PokemonGetHeldItemsUseCase pokemonGetHeldItemsUseCase;
	private final RequestLogCreateUseCase requestLogCreateUseCase;
	private final Mapper<Ability, AbilityResponseSoap> abilityResponseSoapMapper;
	private final Mapper<HeldItem, HeldItemResponseSoap> heldItemResponseSoapMapper;

	@PayloadRoot(namespace = "http://pokemon.demo.mx/", localPart = "AbilityRequestSoap")
	@ResponsePayload
	public AbilityListResponseSoap getAbilities(@RequestPayload AbilityRequestSoap request) throws PokemonClientApiException {
		long startTime = System.currentTimeMillis();

		AbilityListResponseSoap response = new AbilityListResponseSoap()
		  .setAbilities(
			this.pokemonGetAbilitiesUseCase.execute(request.getName())
			  .stream()
			  .map(this.abilityResponseSoapMapper::map)
			  .toList()
		  );

		this.requestLogCreateUseCase.execute(
		  new RequestLogCreateInput(
			OriginIpExtractor.ip(),
			Method.abilities
		  ).setDurationMs(System.currentTimeMillis() - startTime)
			.setRequestObj(request)
			.setResponseObj(response)
		);

		return response;
	}

	@PayloadRoot(namespace = "http://pokemon.demo.mx/", localPart = "BaseExperienceRequestSoap")
	@ResponsePayload
	public BaseExperienceResponseSoap getBaseExperience(
	  @RequestPayload BaseExperienceRequestSoap request
	) throws ResourceNotFoundException, PokemonClientApiException {
		long startTime = System.currentTimeMillis();

		BaseExperienceResponseSoap response = new BaseExperienceResponseSoap().setBaseExperience(
		  this.pokemonGetBaseExperienceUseCase.execute(request.getName())
		);

		this.requestLogCreateUseCase.execute(
		  new RequestLogCreateInput(
			OriginIpExtractor.ip(),
			Method.base_experience
		  ).setDurationMs(System.currentTimeMillis() - startTime)
			.setRequestObj(request)
			.setResponseObj(response)
		);

		return response;
	}

	@PayloadRoot(namespace = "http://pokemon.demo.mx/", localPart = "HeldItemsRequestSoap")
	@ResponsePayload
	public HeldItemListResponseSoap getHeldItems(@RequestPayload HeldItemsRequestSoap request) throws PokemonClientApiException {
		long startTime = System.currentTimeMillis();

		HeldItemListResponseSoap response = new HeldItemListResponseSoap()
		  .setHeldItems(
			this.pokemonGetHeldItemsUseCase.execute(request.getName())
			  .stream()
			  .map(this.heldItemResponseSoapMapper::map)
			  .toList()
		  );

		this.requestLogCreateUseCase.execute(
		  new RequestLogCreateInput(
			OriginIpExtractor.ip(),
			Method.held_items
		  ).setDurationMs(System.currentTimeMillis() - startTime)
			.setRequestObj(request)
			.setResponseObj(response)
		);

		return response;
	}

	@PayloadRoot(namespace = "http://pokemon.demo.mx/", localPart = "IdRequestSoap")
	@ResponsePayload
	public IdResponseSoap getId(
	  @RequestPayload IdRequestSoap request
	) throws ResourceNotFoundException, PokemonClientApiException {
		long startTime = System.currentTimeMillis();

		IdResponseSoap response = new IdResponseSoap().setId(
		  this.pokemonGetIdUseCase.execute(request.getName())
		);

		this.requestLogCreateUseCase.execute(
		  new RequestLogCreateInput(
			OriginIpExtractor.ip(),
			Method.id
		  ).setDurationMs(System.currentTimeMillis() - startTime)
			.setRequestObj(request)
			.setResponseObj(response)
		);

		return response;
	}

	@PayloadRoot(namespace = "http://pokemon.demo.mx/", localPart = "NameRequestSoap")
	@ResponsePayload
	public NameResponseSoap getName(
	  @RequestPayload NameRequestSoap request
	) throws PokemonClientApiException, ResourceNotFoundException {
		long startTime = System.currentTimeMillis();

		NameResponseSoap response = new NameResponseSoap()
		  .setName(this.pokemonGetNameUseCase.execute(request.getName()));

		this.requestLogCreateUseCase.execute(
		  new RequestLogCreateInput(
			OriginIpExtractor.ip(),
			Method.name
		  ).setDurationMs(System.currentTimeMillis() - startTime)
			.setRequestObj(request)
			.setResponseObj(response)
		);

		return response;
	}

	@PayloadRoot(namespace = "http://pokemon.demo.mx/", localPart = "LocationAreEncountersRequestSoap")
	@ResponsePayload
	public LocalAreaEncountersResponseSoap getLocalAreaEncounter(
	  @RequestPayload LocationAreEncountersRequestSoap request
	) throws ResourceNotFoundException, PokemonClientApiException {
		long startTime = System.currentTimeMillis();

		LocalAreaEncountersResponseSoap response = new LocalAreaEncountersResponseSoap().setLocationAreaEncounters(
		  this.pokemonGetLocationAreaEncounterUseCase.execute(request.getName())
		);

		this.requestLogCreateUseCase.execute(
		  new RequestLogCreateInput(
			OriginIpExtractor.ip(),
			Method.location_area_encounters
		  ).setDurationMs(System.currentTimeMillis() - startTime)
			.setRequestObj(request)
			.setResponseObj(response)
		);

		return response;
	}

}