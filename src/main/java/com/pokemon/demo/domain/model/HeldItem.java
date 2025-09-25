package com.pokemon.demo.domain.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Optional;

@Data
@Accessors(chain = true)
public class HeldItem {

	public Resource item;
	public List<HeldItemVersion> versionDetails;

	public Optional<Resource> getItem() {
		return Optional.ofNullable(item);
	}

}
