package cucumber.steps;

import com.pokemon.demo.application.ports.webservice.resources.get_base_experience.request.BaseExperienceRequestSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_base_experience.response.BaseExperienceResponseSoap;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.ws.client.core.WebServiceTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class PokemonBaseExperienceSoapSteps {

	private final WebServiceTemplate webServiceTemplate;
	private final CommonSteps commonSteps;
	private BaseExperienceResponseSoap response;
	@LocalServerPort
	private int port;

	public PokemonBaseExperienceSoapSteps(WebServiceTemplate webServiceTemplate, CommonSteps commonSteps) {
		this.webServiceTemplate = webServiceTemplate;
		this.commonSteps = commonSteps;
	}

	@When("I request Pokemon base experience for {string}")
	public void i_request_pokemon_base_experience_for(String name) {
		BaseExperienceRequestSoap request = new BaseExperienceRequestSoap();
		request.setName(name);

		String uri = "http://localhost:" + port + "/ws";

		Object raw = webServiceTemplate.marshalSendAndReceive(uri, request);
		assertNotNull(raw, "SOAP response should not be null");
		response = (BaseExperienceResponseSoap)raw;
	}

	@Then("I receive its base experience")
	public void i_receive_its_base_experience() {
		assertNotNull(response, "response must not be null");
		assertNotNull(response.getBaseExperience(), "baseExperience must not be null");

		assertThat(response.getBaseExperience()).as("baseExperience should be > 0").isGreaterThan(0);
	}

}
