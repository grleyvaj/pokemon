package com.pokemon.demo.application.handlers.ws;

import com.pokemon.demo.domain.exception.PokemonClientApiException;
import com.pokemon.demo.domain.exception.ResourceNotFoundException;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.soap.SoapFault;
import org.springframework.ws.soap.SoapFaultDetail;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;

import javax.xml.namespace.QName;

@Configuration
public class SoapFaultMappingExceptionHandler extends SoapFaultMappingExceptionResolver {

	private static final QName DESCRIPTION = new QName("description");

	@Override
	protected void customizeFault(Object endpoint, Exception ex, SoapFault fault) {
		if(ex instanceof ResourceNotFoundException) {
			SoapFaultDetail detail = fault.addFaultDetail();
			detail.addFaultDetailElement(DESCRIPTION).addText(ex.getMessage());
		}
		if(ex instanceof PokemonClientApiException) {
			SoapFaultDetail detail = fault.addFaultDetail();
			detail.addFaultDetailElement(DESCRIPTION).addText(ex.getMessage());
		}
	}

}
