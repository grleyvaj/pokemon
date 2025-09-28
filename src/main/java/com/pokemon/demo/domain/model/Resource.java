package com.pokemon.demo.domain.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Resource {

	public final String name;
	public final String url;

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

}
