package cucumber.steps;

import com.pokemon.demo.application.ports.webservice.resources.get_location_area_encounters.request.LocationAreEncountersRequestSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_location_area_encounters.response.LocalAreaEncountersResponseSoap;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.ws.client.core.WebServiceTemplate;

import static org.assertj.core.api.Assertions.assertThat;

public class PokemonLocalAreaEncountersSoapSteps {

	private final WebServiceTemplate webServiceTemplate;
	@LocalServerPort
	private int port;
	private LocationAreEncountersRequestSoap request;
	private LocalAreaEncountersResponseSoap response;

	public PokemonLocalAreaEncountersSoapSteps(WebServiceTemplate webServiceTemplate) {
		this.webServiceTemplate = webServiceTemplate;
	}

	@Given("a Pokemon SOAP LocalAreaEncounters request with name {string}")
	public void a_pokemon_soap_local_area_encounters_request_with_name(String name) {
		request = new LocationAreEncountersRequestSoap();
		request.setName(name);
	}

	@When("I call the SOAP getLocalAreaEncounter operation")
	public void i_call_the_soap_getLocalAreaEncounter_operation() {
		String uri = "http://localhost:" + port + "/ws";
		Object raw = webServiceTemplate.marshalSendAndReceive(uri, request);
		assertThat(raw).as("SOAP response shouldn't be null").isNotNull();
		response = (LocalAreaEncountersResponseSoap)raw;
	}

	@Then("I receive LocalAreaEncounters and the list is not empty")
	public void i_receive_local_area_encounters_and_list_not_empty() {
		assertThat(response).isNotNull();
		assertThat(response.getLocationAreaEncounters())
		  .as("locationAreaEncounters should not be null or empty")
		  .isNotNull()
		  .isNotEmpty();
	}

}
