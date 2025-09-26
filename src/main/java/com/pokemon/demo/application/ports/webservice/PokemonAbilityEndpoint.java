package com.pokemon.demo.application.ports.webservice;

import com.pokemon.demo.application.ports.webservice.get_ability.request.AbilityRequestSoap;
import com.pokemon.demo.application.ports.webservice.get_ability.response.AbilityResponseSoap;
import com.pokemon.demo.application.ports.webservice.get_ability.response.ResourceResponseSoap;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class PokemonAbilityEndpoint {

	@PayloadRoot(namespace = "http://pokemon.demo.mx/", localPart = "AbilityRequestSoap")
	@ResponsePayload
	public AbilityResponseSoap getAbility(@RequestPayload AbilityRequestSoap request) {
		return new AbilityResponseSoap()
		  .setHidden(false)
		  .setSlot(1)
		  .setAbility(
			new ResourceResponseSoap()
			  .setName("overgrow")
			  .setUrl("https://pokeapi.co/api/v2/ability/65/")
		  );
	}

}