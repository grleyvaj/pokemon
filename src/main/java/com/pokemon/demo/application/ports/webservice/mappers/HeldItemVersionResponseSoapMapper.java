package com.pokemon.demo.application.ports.webservice.mappers;

import com.pokemon.demo.application.ports.webservice.resources.get_ability.response.ResourceResponseSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_held_item.response.HeldItemVersionResponseSoap;
import com.pokemon.demo.domain.helper.Mapper;
import com.pokemon.demo.domain.model.HeldItemVersion;
import com.pokemon.demo.domain.model.Resource;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HeldItemVersionResponseSoapMapper implements Mapper<HeldItemVersion, HeldItemVersionResponseSoap> {

	private final Mapper<Resource, ResourceResponseSoap> resourceResponseSoapMapper;

	@Override
	public HeldItemVersionResponseSoap map(HeldItemVersion model) {
		HeldItemVersionResponseSoap heldVersion = new HeldItemVersionResponseSoap();

		model.getRarity().map(heldVersion::setRarity);
		model.getVersion().ifPresent(resource ->
		  heldVersion.setVersion(this.resourceResponseSoapMapper.map(resource))
		);

		return heldVersion;
	}

}
