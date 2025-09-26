package com.pokemon.demo.application.ports.webservice;

import com.pokemon.demo.application.ports.webservice.get_id.request.IdRequestSoap;
import com.pokemon.demo.application.ports.webservice.get_id.response.PokemonIdResponseSoap;
import com.pokemon.demo.domain.exception.PokemonClientApiException;
import com.pokemon.demo.domain.exception.ResourceNotFoundException;
import com.pokemon.demo.domain.use_case.pokemon.get_id.PokemonGetIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@RequiredArgsConstructor
@Endpoint
public class PokemonIdEndpoint {

	private final PokemonGetIdUseCase pokemonGetIdUseCase;

	@PayloadRoot(namespace = "http://pokemon.demo.mx/", localPart = "IdRequestSoap")
	@ResponsePayload
	public PokemonIdResponseSoap getPokemonId(
	  @RequestPayload IdRequestSoap request
	) throws ResourceNotFoundException, PokemonClientApiException {
		return new PokemonIdResponseSoap().setId(
		  this.pokemonGetIdUseCase.execute(request.getName())
		);
	}

}