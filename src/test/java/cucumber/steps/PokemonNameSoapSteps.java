package cucumber.steps;

import com.pokemon.demo.application.ports.webservice.resources.get_name.request.NameRequestSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_name.response.NameResponseSoap;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.ws.client.core.WebServiceTemplate;

import static org.assertj.core.api.Assertions.assertThat;

public class PokemonNameSoapSteps {

	@LocalServerPort
	private int port;

	private final WebServiceTemplate webServiceTemplate;

	private NameRequestSoap request;
	private NameResponseSoap response;

	public PokemonNameSoapSteps(WebServiceTemplate webServiceTemplate) {
		this.webServiceTemplate = webServiceTemplate;
	}

	@Given("a Pokemon SOAP Name request with name {string}")
	public void a_pokemon_soap_name_request_with_name(String name) {
		request = new NameRequestSoap();
		request.setName(name);
	}

	@When("I call the SOAP getName operation")
	public void i_call_the_soap_getName_operation() {
		String uri = "http://localhost:" + port + "/ws"; // ajusta si tu mapping SOAP es otro
		response = (NameResponseSoap) webServiceTemplate.marshalSendAndReceive(uri, request);
	}

	@Then("I receive the Pokemon Name {string}")
	public void i_receive_the_pokemon_name(String expectedName) {
		assertThat(response).isNotNull();
		assertThat(response.getName()).isEqualTo(expectedName);
	}

}