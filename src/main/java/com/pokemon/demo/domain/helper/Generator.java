package com.pokemon.demo.domain.helper;

import com.github.f4b6a3.ulid.UlidCreator;

public class Generator {

	public static String ulid() {
		return UlidCreator.getUlid().toString();
	}

}
