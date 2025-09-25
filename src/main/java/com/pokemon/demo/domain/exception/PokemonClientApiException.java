package com.pokemon.demo.domain.exception;

public class PokemonClientApiException extends Exception {

	public PokemonClientApiException(String message) {
		super(message);
	}

	public PokemonClientApiException(String message, Throwable cause) {
		super(message, cause);
	}

}
