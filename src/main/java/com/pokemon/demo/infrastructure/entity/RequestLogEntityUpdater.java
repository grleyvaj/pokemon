package com.pokemon.demo.infrastructure.entity;

import com.pokemon.demo.domain.helper.Updater;
import com.pokemon.demo.domain.repository.RequestLogUpdateInput;

public class RequestLogEntityUpdater implements Updater<RequestLogEntity, RequestLogUpdateInput> {

	@Override
	public RequestLogEntity update(RequestLogEntity entity, RequestLogUpdateInput input) {

		input.getDurationMs().ifPresent(entity::setDurationMs);
		input.getResponse().ifPresent(entity::setResponse);

		return entity;
	}

}
