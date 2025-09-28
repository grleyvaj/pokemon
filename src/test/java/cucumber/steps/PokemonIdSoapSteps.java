package cucumber.steps;

import com.pokemon.demo.application.ports.webservice.resources.get_id.request.IdRequestSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_id.response.IdResponseSoap;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.ws.client.core.WebServiceTemplate;

import static org.assertj.core.api.Assertions.assertThat;

public class PokemonIdSoapSteps {

	@LocalServerPort
	private int port;

	@Autowired
	private WebServiceTemplate webServiceTemplate;

	private IdRequestSoap request;
	private IdResponseSoap response;

	@Given("a Pokemon SOAP request with name {string}")
	public void a_pokemon_soap_request_with_name(String name) {
		request = new IdRequestSoap();
		request.setName(name);
	}

	@When("I call the SOAP getId operation")
	public void i_call_the_soap_get_id_operation() {
		String uri = "http://localhost:" + port + "/ws";
		response = (IdResponseSoap)webServiceTemplate.marshalSendAndReceive(uri, request);
	}

	@Then("I receive the Pokemon ID")
	public void i_receive_the_pokemon_id() {
		assertThat(response).isNotNull();
		assertThat(response.getId()).isGreaterThan(0);
	}

}