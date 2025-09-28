package cucumber.steps;

import com.pokemon.demo.application.ports.webservice.resources.get_ability.request.AbilityRequestSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_ability.response.AbilityListResponseSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_ability.response.AbilityResponseSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_ability.response.ResourceResponseSoap;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.ws.client.core.WebServiceTemplate;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PokemonAbilitiesSoapSteps {

	private final WebServiceTemplate webServiceTemplate;
	private final CommonSteps commonSteps; // si no lo usas, puedes eliminarlo
	@LocalServerPort
	private int port;
	private AbilityListResponseSoap response;

	public PokemonAbilitiesSoapSteps(WebServiceTemplate webServiceTemplate, CommonSteps commonSteps) {
		this.webServiceTemplate = webServiceTemplate;
		this.commonSteps = commonSteps;
	}

	@When("I request Pokemon abilities for {string}")
	public void i_request_pokemon_abilities_for(String name) {
		AbilityRequestSoap request = new AbilityRequestSoap();
		request.setName(name);

		String uri = "http://localhost:" + port + "/ws";
		Object raw = webServiceTemplate.marshalSendAndReceive(uri, request);
		assertNotNull(raw, "SOAP response should not be null");
		response = (AbilityListResponseSoap
		  )raw;
	}

	@Then("I receive abilities list not empty")
	public void i_receive_abilities_list_not_empty() {
		assertNotNull(response, "response must not be null");
		List<AbilityResponseSoap> abilities = response.getAbilities();
		assertThat(abilities).isNotNull().isNotEmpty();
	}

	@Then("I receive ability {string} with hidden {string} and slot {int}")
	public void i_receive_ability_with_hidden_and_slot(String abilityName, String hiddenStr, int expectedSlot) {
		assertNotNull(response, "response must not be null");
		List<AbilityResponseSoap> abilities = response.getAbilities();
		assertThat(abilities).isNotNull().isNotEmpty();

		Optional<AbilityResponseSoap> found = abilities.stream()
		  .filter(a -> {
			  ResourceResponseSoap r = a.getResource();
			  return r != null && abilityName.equalsIgnoreCase(r.getName());
		  })
		  .findFirst();

		assertThat(found).as("expected ability %s to be present", abilityName).isPresent();

		AbilityResponseSoap ability = found.get();
		boolean expectedHidden = Boolean.parseBoolean(hiddenStr);

		assertThat(ability.isHidden()).as("hidden flag mismatch for " + abilityName).isEqualTo(expectedHidden);
		assertThat(ability.getSlot()).as("slot mismatch for " + abilityName).isEqualTo(expectedSlot);

		ResourceResponseSoap resource = ability.getResource();
		assertNotNull(resource, "resource must not be null for ability " + abilityName);
		assertThat(resource.getName()).isEqualToIgnoringCase(abilityName);

		assertThat(resource.getUrl()).as("resource.url should look like a pokeapi ability url")
		  .contains("/ability/")
		  .endsWith("/");
	}

}
