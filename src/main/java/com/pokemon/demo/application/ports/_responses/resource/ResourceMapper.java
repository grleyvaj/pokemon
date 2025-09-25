package com.pokemon.demo.application.ports._responses.resource;

import com.pokemon.demo.domain.helper.Mapper;
import com.pokemon.demo.domain.model.Resource;

public class ResourceMapper implements Mapper<Resource, ResourceResponse> {

	@Override
	public ResourceResponse map(Resource model) {
		return new ResourceResponse()
		  .setName(model.getName())
		  .setUrl(model.getUrl());
	}

}
