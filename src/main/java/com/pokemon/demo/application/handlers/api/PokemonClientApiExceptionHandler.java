package com.pokemon.demo.application.handlers.api;

import com.pokemon.demo.application.handlers.api.response.ErrorResponse;
import com.pokemon.demo.domain.exception.PokemonClientApiException;
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
public class PokemonClientApiExceptionHandler {

	@ExceptionHandler(PokemonClientApiException.class)
	public ResponseEntity<ErrorResponse> handlePokemonClientApiException(
	  ResourceNotFoundException ex
	) {
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());

		log.debug(ex.getMessage(), ex);

		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}