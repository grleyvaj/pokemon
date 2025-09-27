package com.pokemon.demo.builder;

import com.pokemon.demo.domain.model.Resource;

public class ResourceBuilder extends Builder<Resource> {

	@Override
	public Resource build(int index) {
		return new Resource(
		  "::name-" + index + "::",
		  "::url-" + index + "::"
		);
	}

}
