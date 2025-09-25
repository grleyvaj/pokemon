package com.pokemon.demo.domain.repository;

import com.pokemon.demo.domain.model.RequestLog;

public interface RequestLogRepository {

	void create(RequestLogCreateInput requestLogCreateInput);

	RequestLog update(String id, RequestLogUpdateInput requestLogUpdateInput);

}
