package com.pokemon.demo.infrastructure.api.retrofit;

import com.pokemon.demo.infrastructure.api.dtos.pokemon.PokemonResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonApi {

	@GET("{name}")
	Call<PokemonResponse> getPokemon(@Path("name") String pokemonName);

}