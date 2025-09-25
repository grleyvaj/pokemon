package com.pokemon.demo.application.beans;

import com.pokemon.demo.domain.helper.Mapper;
import com.pokemon.demo.domain.helper.Updater;
import com.pokemon.demo.domain.model.Pokemon;
import com.pokemon.demo.domain.model.RequestLog;
import com.pokemon.demo.domain.repository.PokemonRepository;
import com.pokemon.demo.domain.repository.RequestLogCreateInput;
import com.pokemon.demo.domain.repository.RequestLogRepository;
import com.pokemon.demo.domain.repository.RequestLogUpdateInput;
import com.pokemon.demo.infrastructure.api.dtos.pokemon.PokemonResponse;
import com.pokemon.demo.infrastructure.api.retrofit.PokemonApi;
import com.pokemon.demo.infrastructure.api.retrofit.RetrofitPokemonRepository;
import com.pokemon.demo.infrastructure.entity.RequestLogEntity;
import com.pokemon.demo.infrastructure.mysql.MySqlRequestLogRepository;
import com.pokemon.demo.infrastructure.persistence.JpaRequestLogEntityRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class Repository {

	@Bean
	public PokemonRepository retrofitPokemonRepository(
	  PokemonApi pokemonApi,
	  RetryTemplate retryTemplate,
	  Mapper<PokemonResponse, Pokemon> pokemonMapper
	) {
		return new RetrofitPokemonRepository(
		  pokemonApi,
		  retryTemplate,
		  pokemonMapper
		);
	}

	@Bean
	public RequestLogRepository requestLogRepository(
	  JpaRequestLogEntityRepository jpaRequestLongRepository,
	  Mapper<RequestLogCreateInput, RequestLogEntity> requestLogEntityMapper,
	  Mapper<RequestLogEntity, RequestLog> requestLogMapper,
	  Updater<RequestLogEntity, RequestLogUpdateInput> requestLogUpdateInputUpdater
	) {
		return new MySqlRequestLogRepository(
		  jpaRequestLongRepository,
		  requestLogEntityMapper,
		  requestLogMapper,
		  requestLogUpdateInputUpdater
		);
	}

}
