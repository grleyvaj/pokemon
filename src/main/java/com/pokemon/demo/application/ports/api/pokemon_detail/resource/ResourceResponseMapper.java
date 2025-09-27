package com.pokemon.demo.application.ports.api.pokemon_detail.resource;

import com.pokemon.demo.domain.helper.Mapper;
import com.pokemon.demo.domain.model.Resource;

public class ResourceResponseMapper implements Mapper<Resource, ResourceResponse> {

	@Override
	public ResourceResponse map(Resource model) {
		return new ResourceResponse()
		  .setName(model.getName())
		  .setUrl(model.getUrl());
	}

}
