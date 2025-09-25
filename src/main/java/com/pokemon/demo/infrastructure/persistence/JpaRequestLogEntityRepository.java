package com.pokemon.demo.infrastructure.persistence;

import com.pokemon.demo.infrastructure.entity.RequestLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRequestLogEntityRepository extends JpaRepository<RequestLogEntity, String> {

}
