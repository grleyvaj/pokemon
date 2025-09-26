package com.pokemon.demo.application.beans;

import com.pokemon.demo.application.configuration.rest.ApiClientProperties;
import com.pokemon.demo.application.configuration.rest.ApisProperties;
import com.pokemon.demo.infrastructure.api.retrofit.PokemonApi;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.support.RetryTemplate;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Configuration
public class Apis {

	@Bean
	public PokemonApi pokemonApi(ApisProperties apiProperties) {
		ApiClientProperties properties = apiProperties.getPokemon();
		OkHttpClient client = new OkHttpClient.Builder()
		  .connectTimeout(properties.getConnectTimeoutMs(), TimeUnit.MILLISECONDS)
		  .readTimeout(properties.getReadTimeoutMs(), TimeUnit.MILLISECONDS)
		  .writeTimeout(properties.getWriteTimeoutMs(), TimeUnit.MILLISECONDS)
		  .addInterceptor(chain -> {
			  Request original = chain.request();
			  Request requestWithHeaders = original.newBuilder().build();
			  return chain.proceed(requestWithHeaders);
		  })
		  .build();

		return new Retrofit.Builder()
		  .baseUrl(properties.getBaseUrl())
		  .client(client)
		  .addConverterFactory(JacksonConverterFactory.create())
		  .build()
		  .create(PokemonApi.class);
	}

	@Bean
	public RetryTemplate retryTemplate(ApisProperties apiProperties) {
		ApiClientProperties pokemonApiProperties = apiProperties.getPokemon();
		return RetryTemplate.builder()
		  .maxAttempts(pokemonApiProperties.getMaxRetries())
		  .fixedBackoff(pokemonApiProperties.getDelayMs())
		  .retryOn(IOException.class)
		  .build();
	}

}
