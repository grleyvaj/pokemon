package com.pokemon.demo.application.beans;

import com.pokemon.demo.application.ports.api.pokemon_detail.PokemonDetailResponse;
import com.pokemon.demo.application.ports.api.pokemon_detail.PokemonDetailResponseMapper;
import com.pokemon.demo.application.ports.api.pokemon_detail.ability.AbilityResponse;
import com.pokemon.demo.application.ports.api.pokemon_detail.ability.AbilityResponseMapper;
import com.pokemon.demo.application.ports.api.pokemon_detail.helds.HeldItemResponse;
import com.pokemon.demo.application.ports.api.pokemon_detail.helds.HeldItemResponseMapper;
import com.pokemon.demo.application.ports.api.pokemon_detail.helds.HeldItemVersionResponse;
import com.pokemon.demo.application.ports.api.pokemon_detail.helds.HeldItemVersionResponseMapper;
import com.pokemon.demo.application.ports.api.pokemon_detail.resource.ResourceResponseMapper;
import com.pokemon.demo.application.ports.api.pokemon_detail.resource.ResourceResponse;
import com.pokemon.demo.application.ports.webservice.mappers.AbilityResponseSoapMapper;
import com.pokemon.demo.application.ports.webservice.mappers.HeldItemResponseSoapMapper;
import com.pokemon.demo.application.ports.webservice.mappers.HeldItemVersionResponseSoapMapper;
import com.pokemon.demo.application.ports.webservice.mappers.ResourceResponseSoapMapper;
import com.pokemon.demo.application.ports.webservice.resources.get_ability.response.AbilityResponseSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_ability.response.ResourceResponseSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_held_item.response.HeldItemResponseSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_held_item.response.HeldItemVersionResponseSoap;
import com.pokemon.demo.domain.helper.ListMapper;
import com.pokemon.demo.domain.helper.Mapper;
import com.pokemon.demo.domain.helper.Updater;
import com.pokemon.demo.domain.model.*;
import com.pokemon.demo.domain.repository.RequestLogCreateInput;
import com.pokemon.demo.domain.repository.RequestLogUpdateInput;
import com.pokemon.demo.infrastructure.api.dtos.pokemon.PokemonMapper;
import com.pokemon.demo.infrastructure.api.dtos.pokemon.PokemonResponse;
import com.pokemon.demo.infrastructure.entity.RequestLogEntity;
import com.pokemon.demo.infrastructure.entity.RequestLogEntityMapper;
import com.pokemon.demo.infrastructure.entity.RequestLogEntityUpdater;
import com.pokemon.demo.infrastructure.entity.RequestLogMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Mappers {

	@Bean
	public Mapper<PokemonResponse, Pokemon> pokemonResponseMapper() {
		return new PokemonMapper();
	}

	@Bean
	public Mapper<Pokemon, PokemonDetailResponse> pokemonDetailResponseMapper(
	  ListMapper<Ability, AbilityResponse> abilityResponseListMapper,
	  ListMapper<HeldItem, HeldItemResponse> heldItemResponseListMapper
	) {
		return new PokemonDetailResponseMapper(abilityResponseListMapper, heldItemResponseListMapper);
	}

	@Bean
	public Mapper<Resource, ResourceResponse> resourceResponseMapper() {
		return new ResourceResponseMapper();
	}

	@Bean
	public Mapper<Ability, AbilityResponse> abilityResponseMapper(
	  Mapper<Resource, ResourceResponse> resourceResponseMapper
	) {
		return new AbilityResponseMapper(resourceResponseMapper);
	}

	@Bean
	public Mapper<HeldItemVersion, HeldItemVersionResponse> heldItemVersionResponseMapper() {
		return new HeldItemVersionResponseMapper();
	}

	@Bean
	public ListMapper<HeldItemVersion, HeldItemVersionResponse> heldItemVersionResponseListMapper(
	  Mapper<HeldItemVersion, HeldItemVersionResponse> heldItemVersionResponseMapper
	) {
		return new ListMapper<>(heldItemVersionResponseMapper);
	}

	@Bean
	public ListMapper<Ability, AbilityResponse> abilityResponseListMapper(
	  Mapper<Ability, AbilityResponse> abilityResponseMapper
	) {
		return new ListMapper<>(abilityResponseMapper);
	}

	@Bean
	public Mapper<HeldItem, HeldItemResponse> heldItemResponseMapper(
	  Mapper<Resource, ResourceResponse> resourceResponseMapper,
	  ListMapper<HeldItemVersion, HeldItemVersionResponse> heldItemVersionResponseListMapper
	) {
		return new HeldItemResponseMapper(
		  resourceResponseMapper,
		  heldItemVersionResponseListMapper
		);
	}

	@Bean
	public ListMapper<HeldItem, HeldItemResponse> heldItemResponseListMapper(
	  Mapper<HeldItem, HeldItemResponse> heldItemResponseMapper
	) {
		return new ListMapper<>(heldItemResponseMapper);
	}

	@Bean
	public Mapper<RequestLogCreateInput, RequestLogEntity> requestLogEntityMapper() {
		return new RequestLogEntityMapper();
	}

	@Bean
	public Updater<RequestLogEntity, RequestLogUpdateInput> requestLogEntityUpdater() {
		return new RequestLogEntityUpdater();
	}

	@Bean
	public Mapper<RequestLogEntity, RequestLog> requestLogMapper() {
		return new RequestLogMapper();
	}

	@Bean
	public Mapper<Resource, ResourceResponseSoap> resourceResponseSoapMapper() {
		return new ResourceResponseSoapMapper();
	}

	@Bean
	public Mapper<Ability, AbilityResponseSoap> abilityAbilityResponseSoapMapper(
	  Mapper<Resource, ResourceResponseSoap> resourceResponseSoapMapper
	) {
		return new AbilityResponseSoapMapper(resourceResponseSoapMapper);
	}

	@Bean
	public Mapper<HeldItemVersion, HeldItemVersionResponseSoap> heldItemVersionResponseSoapMapper(
	  Mapper<Resource, ResourceResponseSoap> resourceResponseSoapMapper
	) {
		return new HeldItemVersionResponseSoapMapper(resourceResponseSoapMapper);
	}

	@Bean
	public ListMapper<HeldItemVersion, HeldItemVersionResponseSoap> heldItemVersionResponseSoapListMapper(
	  Mapper<HeldItemVersion, HeldItemVersionResponseSoap> heldItemVersionResponseSoapMapper
	) {
		return new ListMapper<>(heldItemVersionResponseSoapMapper);
	}

	@Bean
	public Mapper<HeldItem, HeldItemResponseSoap> heldItemResponseSoapMapper(
	  Mapper<Resource, ResourceResponseSoap> resourceResponseSoapMapper,
	  ListMapper<HeldItemVersion, HeldItemVersionResponseSoap> heldItemVersionResponseSoapListMapper
	) {
		return new HeldItemResponseSoapMapper(
		  resourceResponseSoapMapper,
		  heldItemVersionResponseSoapListMapper
		);
	}

}
