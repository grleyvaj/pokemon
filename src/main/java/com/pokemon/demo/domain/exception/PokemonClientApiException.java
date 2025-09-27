package com.pokemon.demo.domain.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.SERVER)
public class PokemonClientApiException extends RuntimeException {

	public PokemonClientApiException(String message) {
		super(message);
	}

	public PokemonClientApiException(String message, Throwable cause) {
		super(message, cause);
	}

}
