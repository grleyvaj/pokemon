package com.pokemon.demo.application.beans;

import com.pokemon.demo.domain.repository.PokemonRepository;
import com.pokemon.demo.domain.repository.RequestLogRepository;
import com.pokemon.demo.domain.use_case.pokemon.detail.PokemonDetailUseCase;
import com.pokemon.demo.domain.use_case.pokemon.get_id.PokemonGetIdUseCase;
import com.pokemon.demo.domain.use_case.pokemon.get_name.PokemonGetNameUseCase;
import com.pokemon.demo.domain.use_case.request_log.RequestLogCreateUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCases {

	@Bean
	public PokemonDetailUseCase pokemonDetailUseCase(PokemonRepository pokemonRepository) {
		return new PokemonDetailUseCase(pokemonRepository);
	}

	@Bean
	public RequestLogCreateUseCase requestLogCreateUseCase(RequestLogRepository requestLogRepository) {
		return new RequestLogCreateUseCase(requestLogRepository);
	}

	@Bean
	public PokemonGetIdUseCase pokemonGetIdUseCase(PokemonRepository pokemonRepository) {
		return new PokemonGetIdUseCase(pokemonRepository);
	}

	@Bean
	public PokemonGetNameUseCase pokemonGetNameUseCase(PokemonRepository pokemonRepository) {
		return new PokemonGetNameUseCase(pokemonRepository);
	}

}
