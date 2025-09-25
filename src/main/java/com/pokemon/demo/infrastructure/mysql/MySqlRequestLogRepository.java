package com.pokemon.demo.infrastructure.mysql;

import com.pokemon.demo.domain.helper.Mapper;
import com.pokemon.demo.domain.helper.Updater;
import com.pokemon.demo.domain.exception.ResourceNotFoundException;
import com.pokemon.demo.domain.model.RequestLog;
import com.pokemon.demo.domain.repository.RequestLogCreateInput;
import com.pokemon.demo.domain.repository.RequestLogRepository;
import com.pokemon.demo.domain.repository.RequestLogUpdateInput;
import com.pokemon.demo.infrastructure.entity.RequestLogEntity;
import com.pokemon.demo.infrastructure.persistence.JpaRequestLogEntityRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MySqlRequestLogRepository implements RequestLogRepository {

	private final JpaRequestLogEntityRepository repository;
	private final Mapper<RequestLogCreateInput, RequestLogEntity> requestLogEntityMapper;
	private final Mapper<RequestLogEntity, RequestLog> requestLogMapper;
	private final Updater<RequestLogEntity, RequestLogUpdateInput> requestLogUpdateInputUpdater;

	@Override
	public void create(RequestLogCreateInput requestLogCreateInput) {
		this.repository.save(
		  this.requestLogEntityMapper.map(requestLogCreateInput)
		);
	}

	@Override
	public RequestLog update(String id, RequestLogUpdateInput requestLogUpdateInput) {
		RequestLogEntity requestLogEntity = this.repository.findById(id)
		  .orElseThrow(() -> new ResourceNotFoundException("Pokemon with ID %s not found".formatted(id)));

		return this.requestLogMapper.map(
		  this.repository.save(
			this.requestLogUpdateInputUpdater.update(requestLogEntity, requestLogUpdateInput)
		  )
		);
	}

}
