package com.pokemon.demo.builder;

import com.pokemon.demo.application.ports.api.pokemon_detail.resource.ResourceResponse;

public class ResourceResponseBuilder extends Builder<ResourceResponse> {

	@Override
	public ResourceResponse build(int index) {
		return new ResourceResponse()
		  .setName("::name-" + index + "::")
		  .setUrl("::url-" + index + "::");
	}

}
