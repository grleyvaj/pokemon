package com.pokemon.demo.builder;

import com.pokemon.demo.application.ports.webservice.resources.get_ability.response.ResourceResponseSoap;

public class ResourceResponseSoapBuilder extends Builder<ResourceResponseSoap> {

	@Override
	public ResourceResponseSoap build(int index) {
		return new ResourceResponseSoap()
		  .setName("::name-" + index + "::")
		  .setUrl("::url-" + index + "::");
	}

}
