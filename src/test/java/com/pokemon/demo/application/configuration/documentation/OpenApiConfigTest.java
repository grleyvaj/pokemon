package com.pokemon.demo.application.configuration.documentation;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.junit.jupiter.api.Test;
import org.springframework.boot.info.BuildProperties;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OpenApiConfigTest {

	@Test
	void test_when_openApi_with_custom_properties_then_returns_openApi_with_custom_values() {
		BuildProperties buildProperties = mock(BuildProperties.class);
		when(buildProperties.getVersion()).thenReturn("2.3.4");

		OpenApiProperties props = new OpenApiProperties();
		OpenApiProperties.Info infoProps = new OpenApiProperties.Info();
		OpenApiProperties.Contact contact = new OpenApiProperties.Contact();
		contact.setName("Ash");
		contact.setEmail("ash@pokeapi.com");
		infoProps.setTitle("Custom API");
		infoProps.setDescription("Custom description");
		infoProps.setContact(contact);
		props.setInfo(infoProps);

		OpenApiProperties.Server server = new OpenApiProperties.Server();
		server.setUrl("http://localhost:8080");
		server.setDescription("Local server");
		props.setServers(List.of(server));

		OpenApiConfig config = new OpenApiConfig(props, Optional.of(buildProperties));
		OpenAPI openAPI = config.openAPI();

		Info info = openAPI.getInfo();
		assertThat(info.getTitle()).isEqualTo("Custom API");
		assertThat(info.getDescription()).isEqualTo("Custom description");
		assertThat(info.getVersion()).isEqualTo("2.3.4");

		Contact c = info.getContact();
		assertThat(c).isNotNull();
		assertThat(c.getName()).isEqualTo("Ash");
		assertThat(c.getEmail()).isEqualTo("ash@pokeapi.com");

		assertThat(openAPI.getServers())
		  .hasSize(1)
		  .extracting(Server::getUrl, Server::getDescription)
		  .containsExactly(tuple("http://localhost:8080", "Local server"));
	}

	@Test
	void test_when_openApi_with_defaults_then_returns_openApi_with_default_values() {
		OpenApiProperties props = new OpenApiProperties();
		props.setInfo(null);
		OpenApiConfig config = new OpenApiConfig(props, Optional.empty());

		OpenAPI openAPI = config.openAPI();

		assertThat(openAPI.getInfo().getTitle()).isEqualTo("POKEMON");
		assertThat(openAPI.getInfo().getDescription()).isEqualTo("POKEMON SOAP SERVICE");
		assertThat(openAPI.getInfo().getVersion()).isEqualTo("1.0");
		assertThat(openAPI.getInfo().getContact()).isNull();
		assertThat(openAPI.getServers()).isNullOrEmpty();
	}

}