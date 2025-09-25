package com.pokemon.demo.infrastructure.entity;

import com.pokemon.demo.domain.helper.Mapper;
import com.pokemon.demo.domain.model.RequestLog;

import java.util.Optional;

public class RequestLogMapper implements Mapper<RequestLogEntity, RequestLog> {

	@Override
	public RequestLog map(RequestLogEntity entity) {
		RequestLog model = new RequestLog(
		  entity.getId(),
		  entity.getOriginIp(),
		  entity.getRequestDate(),
		  entity.getMethod()
		);

		Optional.ofNullable(entity.getDurationMs()).ifPresent(model::setDurationMs);
		Optional.ofNullable(entity.getRequest()).ifPresent(model::setRequest);
		Optional.ofNullable(entity.getResponse()).ifPresent(model::setResponse);

		return model;
	}

}
