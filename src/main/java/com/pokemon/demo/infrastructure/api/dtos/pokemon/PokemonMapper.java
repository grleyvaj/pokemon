package com.pokemon.demo.infrastructure.api.dtos.pokemon;

import com.pokemon.demo.domain.helper.Mapper;
import com.pokemon.demo.domain.model.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class PokemonMapper implements Mapper<PokemonResponse, Pokemon> {

	@Override
	public Pokemon map(PokemonResponse response) {
		List<Ability> abilities = response.getAbilities()
		  .stream()
		  .map(
			item -> new Ability(item.isHidden(), item.getSlot())
			  .setResource(new Resource(item.getAbility().getName(), item.getAbility().getUrl()))
		  )
		  .toList();

		List<HeldItem> heldItems = Optional.ofNullable(response.getHeldItems())
		  .orElse(Collections.emptyList())
		  .stream()
		  .filter(Objects::nonNull)
		  .map(ph -> {
			  Resource itemResource = Optional.ofNullable(ph.getItem())
				.map(i -> new Resource(i.getName(), i.getUrl()))
				.orElse(null);

			  List<HeldItemVersion> versions = Optional.ofNullable(ph.getVersionDetails())
				.orElse(Collections.emptyList())
				.stream()
				.filter(Objects::nonNull)
				.map(version -> new HeldItemVersion()
				  .setVersion(Optional.ofNullable(version.getVersion())
					.map(resource -> new Resource(resource.getName(), resource.getUrl()))
					.orElse(null))
				  .setRarity(version.getRarity()))
				.collect(Collectors.toList());

			  return new HeldItem()
				.setItem(itemResource)
				.setVersionDetails(versions);
		  })
		  .toList();

		return new Pokemon(
		  response.getId(),
		  response.getName(),
		  response.getBaseExperience(),
		  response.getLocationAreaEncounters()
		)
		  .setAbilities(abilities)
		  .setHeldItems(heldItems);
	}

}
