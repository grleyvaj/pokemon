package com.pokemon.demo.application.configuration.soap;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.io.File;

@EnableWs
@Configuration
public class SoapWebServiceConfig {

	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(context);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<>(servlet, "/ws/*");
	}

	@Bean
	public XsdSchema pokemonSchema() {
		File xsdDir = new File("target/generated-resources/xsd");
		File schemaFile = new File(xsdDir, "schema1.xsd");

		if(!schemaFile.exists()) {
			throw new IllegalStateException("schema1.xsd not found in " + xsdDir.getAbsolutePath());
		}

		return new SimpleXsdSchema(new org.springframework.core.io.FileSystemResource(schemaFile));
	}

	@Bean(name = "pokemon")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema pokemonSchema) {
		DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
		definition.setPortTypeName("PokemonPort");
		definition.setLocationUri("/ws");
		definition.setTargetNamespace("http://pokemon.demo.mx/");
		definition.setSchema(pokemonSchema);
		return definition;
	}

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setPackagesToScan("com.pokemon.demo.application.ports.webservice");
		return marshaller;
	}

	@Bean
	@ConditionalOnMissingBean(WebServiceTemplate.class)
	public WebServiceTemplate webServiceTemplate(Marshaller marshaller, Unmarshaller unmarshaller) {
		WebServiceTemplate template = new WebServiceTemplate();
		template.setMarshaller(marshaller);
		template.setUnmarshaller(unmarshaller);
		return template;
	}

}
