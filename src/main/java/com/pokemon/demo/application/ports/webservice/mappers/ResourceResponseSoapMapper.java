package com.pokemon.demo.application.ports.webservice.mappers;

import com.pokemon.demo.application.ports.webservice.resources.get_ability.response.ResourceResponseSoap;
import com.pokemon.demo.domain.helper.Mapper;
import com.pokemon.demo.domain.model.Resource;

public class ResourceResponseSoapMapper implements Mapper<Resource, ResourceResponseSoap> {

	@Override
	public ResourceResponseSoap map(Resource model) {
		return new ResourceResponseSoap()
		  .setName(model.getName())
		  .setUrl(model.getUrl());
	}

}
