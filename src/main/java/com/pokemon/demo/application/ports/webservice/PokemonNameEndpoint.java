package com.pokemon.demo.application.ports.webservice;

import com.pokemon.demo.application.ports.webservice.get_name.request.NameRequestSoap;
import com.pokemon.demo.application.ports.webservice.get_name.response.PokemonNameResponseSoap;
import com.pokemon.demo.domain.exception.PokemonClientApiException;
import com.pokemon.demo.domain.exception.ResourceNotFoundException;
import com.pokemon.demo.domain.use_case.pokemon.get_name.PokemonGetNameUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@RequiredArgsConstructor
@Endpoint
public class PokemonNameEndpoint {

	private final PokemonGetNameUseCase pokemonGetNameUseCase;

	@PayloadRoot(namespace = "http://pokemon.demo.mx/", localPart = "NameRequestSoap")
	@ResponsePayload
	public PokemonNameResponseSoap getPokemonId(
	  @RequestPayload NameRequestSoap request
	) throws PokemonClientApiException, ResourceNotFoundException {
		return new PokemonNameResponseSoap()
		  .setName(this.pokemonGetNameUseCase.execute(request.getName()));
	}

}