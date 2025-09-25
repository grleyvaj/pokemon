package com.pokemon.demo.application.ports._responses.ability;

import com.pokemon.demo.domain.helper.Mapper;
import com.pokemon.demo.application.ports._responses.resource.ResourceResponse;
import com.pokemon.demo.domain.model.Ability;
import com.pokemon.demo.domain.model.Resource;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AbilityResponseMapper implements Mapper<Ability, AbilityResponse> {

	private final Mapper<Resource, ResourceResponse> resourceResponseMapper;

	@Override
	public AbilityResponse map(Ability model) {
		AbilityResponse ability = new AbilityResponse()
		  .setHidden(model.isHidden())
		  .setSlot(model.getSlot());

		model.getResource().ifPresent(resource ->
		  ability.setAbility(this.resourceResponseMapper.map(resource))
		);

		return ability;
	}

}
