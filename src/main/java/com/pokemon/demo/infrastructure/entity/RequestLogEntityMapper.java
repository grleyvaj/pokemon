package com.pokemon.demo.infrastructure.entity;

import com.pokemon.demo.domain.helper.Mapper;
import com.pokemon.demo.domain.helper.Generator;
import com.pokemon.demo.domain.repository.RequestLogCreateInput;

public class RequestLogEntityMapper implements Mapper<RequestLogCreateInput, RequestLogEntity> {

	@Override
	public RequestLogEntity map(RequestLogCreateInput input) {
		RequestLogEntity entity = new RequestLogEntity()
		  .setId(Generator.ulid())
		  .setOriginIp(input.getOriginIp())
		  .setRequestDate(input.getRequestDate())
		  .setMethod(input.getMethod());

		input.getDurationMs().ifPresent(entity::setDurationMs);
		input.getRequest().ifPresent(entity::setRequest);
		input.getResponse().ifPresent(entity::setResponse);

		return entity;
	}

}
