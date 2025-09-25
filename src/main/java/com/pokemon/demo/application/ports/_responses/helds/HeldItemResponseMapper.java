package com.pokemon.demo.application.ports._responses.helds;

import com.pokemon.demo.domain.helper.ListMapper;
import com.pokemon.demo.domain.helper.Mapper;
import com.pokemon.demo.application.ports._responses.resource.ResourceResponse;
import com.pokemon.demo.domain.model.HeldItem;
import com.pokemon.demo.domain.model.HeldItemVersion;
import com.pokemon.demo.domain.model.Resource;
import lombok.RequiredArgsConstructor;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
public class HeldItemResponseMapper implements Mapper<HeldItem, HeldItemResponse> {

	private final Mapper<Resource, ResourceResponse> resourceResponseMapper;
	private final ListMapper<HeldItemVersion, HeldItemVersionResponse> heldItemVersionResponseListMapper;

	@Override
	public HeldItemResponse map(HeldItem model) {
		HeldItemResponse heldItem = new HeldItemResponse();

		model.getItem().ifPresent(item -> heldItem.setItem(this.resourceResponseMapper.map(item)));

		if(nonNull(model.getVersionDetails()) && !model.getVersionDetails().isEmpty()) {
			heldItem.setVersionDetails(this.heldItemVersionResponseListMapper.map(model.versionDetails));
		}

		return heldItem;
	}

}
