package com.pokemon.demo.domain.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Optional;

@Data
@Accessors(chain = true)
public class Ability {

	public final boolean isHidden;
	public final int slot;
	public Resource resource;

	public Optional<Resource> getResource() {
		return Optional.ofNullable(resource);
	}

}
