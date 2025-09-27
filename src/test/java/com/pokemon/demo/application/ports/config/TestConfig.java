package com.pokemon.demo.application.ports.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.SoapVersion;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;

@TestConfiguration
public class TestConfig {

	@Bean
	public Jaxb2Marshaller jaxb2Marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setPackagesToScan("com.pokemon.demo.application.ports.webservice.resources");
		return marshaller;
	}

	@Bean
	public SaajSoapMessageFactory messageFactory() {
		SaajSoapMessageFactory factory = new SaajSoapMessageFactory();
		factory.setSoapVersion(SoapVersion.SOAP_11);
		return factory;
	}

	@Bean
	public WebServiceTemplate webServiceTemplate(Jaxb2Marshaller marshaller, SaajSoapMessageFactory messageFactory) {
		WebServiceTemplate template = new WebServiceTemplate(messageFactory);
		template.setMarshaller(marshaller);
		template.setUnmarshaller(marshaller);
		return template;
	}

}
