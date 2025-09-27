package com.pokemon.demo.application.ports.webservice.mappers;

import com.pokemon.demo.application.ports.webservice.resources.get_ability.response.ResourceResponseSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_held_item.response.HeldItemResponseSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_held_item.response.HeldItemVersionResponseSoap;
import com.pokemon.demo.domain.helper.ListMapper;
import com.pokemon.demo.domain.helper.Mapper;
import com.pokemon.demo.domain.model.HeldItem;
import com.pokemon.demo.domain.model.HeldItemVersion;
import com.pokemon.demo.domain.model.Resource;
import lombok.RequiredArgsConstructor;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
public class HeldItemResponseSoapMapper implements Mapper<HeldItem, HeldItemResponseSoap> {

	private final Mapper<Resource, ResourceResponseSoap> resourceResponseSoapMapper;
	private final ListMapper<HeldItemVersion, HeldItemVersionResponseSoap> heldItemVersionResponseSoapListMapper;

	@Override
	public HeldItemResponseSoap map(HeldItem model) {
		HeldItemResponseSoap heldItem = new HeldItemResponseSoap();

		model.getItem().ifPresent(item -> heldItem.setItem(this.resourceResponseSoapMapper.map(item)));

		if(nonNull(model.getVersionDetails()) && !model.getVersionDetails().isEmpty()) {
			heldItem.setVersionDetails(this.heldItemVersionResponseSoapListMapper.map(model.versionDetails));
		}

		return heldItem;
	}

}
