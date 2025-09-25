package com.pokemon.demo.domain.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Optional;

@Data
@Accessors(chain = true)
public class HeldItemVersion {

	private int rarity;
	private Resource version;

	public Optional<Integer> getRarity() {
		return Optional.ofNullable(rarity);
	}

	public Optional<Resource> getVersion() {
		return Optional.ofNullable(version);
	}

}
