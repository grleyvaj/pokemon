package com.pokemon.demo.domain.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class Pokemon {

	private final int id;
	private final String name;
	private final int baseExperience;
	private final String locationAreaEncounters;
	private List<Ability> abilities;
	private List<HeldItem> heldItems;

}
