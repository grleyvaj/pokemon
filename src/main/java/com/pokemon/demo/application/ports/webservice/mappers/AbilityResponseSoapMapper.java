package com.pokemon.demo.application.ports.webservice.mappers;

import com.pokemon.demo.application.ports.webservice.resources.get_ability.response.AbilityResponseSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_ability.response.ResourceResponseSoap;
import com.pokemon.demo.domain.helper.Mapper;
import com.pokemon.demo.domain.model.Ability;
import com.pokemon.demo.domain.model.Resource;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AbilityResponseSoapMapper implements Mapper<Ability, AbilityResponseSoap> {

	private final Mapper<Resource, ResourceResponseSoap> resourceResponseSoapMapper;

	@Override
	public AbilityResponseSoap map(Ability model) {
		AbilityResponseSoap ability = new AbilityResponseSoap()
		  .setHidden(model.isHidden())
		  .setSlot(model.getSlot());

		model.getResource().ifPresent(resource ->
		  ability.setResource(this.resourceResponseSoapMapper.map(resource))
		);

		return ability;
	}

}
