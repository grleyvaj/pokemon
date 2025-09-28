package com.pokemon.demo.application.handlers.api;

import com.pokemon.demo.application.handlers.api.response.ErrorResponse;
import com.pokemon.demo.domain.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
@Hidden
public class ResourceNotFoundExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
	  ResourceNotFoundException ex
	) {
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());

		log.debug(ex.getMessage(), ex);

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

}