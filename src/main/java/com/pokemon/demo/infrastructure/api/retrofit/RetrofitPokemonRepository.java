package com.pokemon.demo.infrastructure.api.retrofit;

import com.pokemon.demo.domain.helper.Mapper;
import com.pokemon.demo.domain.exception.PokemonClientApiException;
import com.pokemon.demo.domain.exception.ResourceNotFoundException;
import com.pokemon.demo.domain.model.Pokemon;
import com.pokemon.demo.domain.repository.PokemonRepository;
import com.pokemon.demo.infrastructure.api.dtos.pokemon.PokemonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.support.RetryTemplate;
import retrofit2.Response;

import static java.util.Objects.nonNull;

@Slf4j
@RequiredArgsConstructor
public class RetrofitPokemonRepository implements PokemonRepository {

	private final PokemonApi pokemonApi;
	private final RetryTemplate retryTemplate;
	private final Mapper<PokemonResponse, Pokemon> pokemonMapper;

	@Override
	public Pokemon getPokemon(String pokemonName) throws ResourceNotFoundException, PokemonClientApiException {
		try {
			return this.retryTemplate.execute(context -> {
				int attempt = context.getRetryCount() + 1;
				Response<PokemonResponse> response = this.pokemonApi.getPokemon(pokemonName).execute();
				log.info("Calling Pokemon API [attempt #{}] for [{}]", attempt, pokemonName);

				if(response.isSuccessful() && nonNull(response.body())) {
					return this.pokemonMapper.map(response.body());
				}

				int statusCode = response.code();
				if(statusCode == 404) {
					throw new ResourceNotFoundException("Pokemon with NAME %s not found".formatted(pokemonName));
				}

				throw new PokemonClientApiException("Error calling Pokemon API. Status: " + statusCode);
			});
		} catch(Exception e) {
			throw new PokemonClientApiException("I/O error calling Pokemon API after retries", e);
		}
	}

}
