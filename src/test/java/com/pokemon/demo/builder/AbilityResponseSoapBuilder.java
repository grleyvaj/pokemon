package com.pokemon.demo.builder;

import com.pokemon.demo.application.ports.webservice.resources.get_ability.response.AbilityResponseSoap;

public class AbilityResponseSoapBuilder extends Builder<AbilityResponseSoap> {

	@Override
	public AbilityResponseSoap build(int index) {
		return new AbilityResponseSoap()
		  .setHidden(index % 2 != 0)
		  .setSlot(index)
		  .setResource(new ResourceResponseSoapBuilder().build(index * 5));
	}

}
