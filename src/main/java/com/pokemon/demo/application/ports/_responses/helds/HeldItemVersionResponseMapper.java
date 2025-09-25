package com.pokemon.demo.application.ports._responses.helds;

import com.pokemon.demo.domain.helper.Mapper;
import com.pokemon.demo.domain.model.HeldItemVersion;

public class HeldItemVersionResponseMapper implements Mapper<HeldItemVersion, HeldItemVersionResponse> {

	@Override
	public HeldItemVersionResponse map(HeldItemVersion model) {
		HeldItemVersionResponse heldVersion = new HeldItemVersionResponse();

		model.getVersion().ifPresent(heldVersion::setVersion);
		model.getRarity().map(heldVersion::setRarity);

		return heldVersion;
	}

}
