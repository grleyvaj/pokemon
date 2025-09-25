package com.pokemon.demo.infrastructure.entity;


import com.pokemon.demo.domain.helper.Generator;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

import static java.util.Objects.isNull;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "request_logs")
public class RequestLogEntity {

	@Id
	@Column(name = "id", length = 26)
	private String id;

	@Column(name = "origin_ip", nullable = false, length = 45)
	private String originIp;

	@Column(name = "request_date", nullable = false)
	private LocalDateTime requestDate;

	@Column(name = "method", nullable = false)
	private String method;

	@Column(name = "duration_ms")
	private Long durationMs;

	@Lob
	@Column(name = "request")
	private String request;

	@Lob
	@Column(name = "response")
	private String response;

	@PrePersist
	protected void onCreate() {
		if(isNull(this.id)) {
			this.id = Generator.ulid();
		}
	}

}
