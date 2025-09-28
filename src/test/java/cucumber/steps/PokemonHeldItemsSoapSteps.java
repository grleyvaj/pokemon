package cucumber.steps;

import com.pokemon.demo.application.ports.webservice.resources.get_ability.response.ResourceResponseSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_held_item.request.HeldItemsRequestSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_held_item.response.HeldItemListResponseSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_held_item.response.HeldItemResponseSoap;
import com.pokemon.demo.application.ports.webservice.resources.get_held_item.response.HeldItemVersionResponseSoap;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.ws.client.core.WebServiceTemplate;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PokemonHeldItemsSoapSteps {

	private final WebServiceTemplate webServiceTemplate;
	@LocalServerPort
	private int port;
	private HeldItemListResponseSoap response;

	public PokemonHeldItemsSoapSteps(WebServiceTemplate webServiceTemplate) {
		this.webServiceTemplate = webServiceTemplate;
	}

	@When("I request Pokemon held items for {string}")
	public void i_request_pokemon_held_items_for(String name) {
		HeldItemsRequestSoap request = new HeldItemsRequestSoap();
		request.setName(name);

		String uri = "http://localhost:" + port + "/ws";
		Object raw = webServiceTemplate.marshalSendAndReceive(uri, request);
		assertNotNull(raw, "SOAP response should not be null");
		response = (HeldItemListResponseSoap)raw;
	}

	@Then("I receive held items list not empty")
	public void i_receive_held_items_list_not_empty() {
		assertNotNull(response, "response must not be null");
		List<HeldItemResponseSoap> heldItems = response.getHeldItems();
		assertThat(heldItems).isNotNull().isNotEmpty();
	}

	@Then("I receive held item {string} present")
	public void i_receive_held_item_present(String expectedItemName) {
		assertNotNull(response, "response must not be null");
		List<HeldItemResponseSoap> heldItems = response.getHeldItems();
		assertThat(heldItems).isNotNull().isNotEmpty();

		Optional<HeldItemResponseSoap> found = heldItems.stream()
		  .filter(h -> {
			  ResourceResponseSoap item = h.getItem();
			  return item != null && expectedItemName.equalsIgnoreCase(item.getName());
		  })
		  .findFirst();

		assertThat(found).as("expected held item %s to be present", expectedItemName).isPresent();
	}

	@Then("the held item {string} has version {string} with rarity {int}")
	public void the_held_item_has_version_with_rarity(
	  String expectedItemName,
	  String expectedVersionName,
	  int expectedRarity
	) {
		assertNotNull(response, "response must not be null");
		List<HeldItemResponseSoap> heldItems = response.getHeldItems();
		assertThat(heldItems).isNotNull().isNotEmpty();

		HeldItemResponseSoap heldItem = heldItems.stream()
		  .filter(h -> {
			  ResourceResponseSoap item = h.getItem();
			  return item != null && expectedItemName.equalsIgnoreCase(item.getName());
		  })
		  .findFirst()
		  .orElseThrow(() -> new AssertionError("Held item '" + expectedItemName + "' not found"));

		List<HeldItemVersionResponseSoap> versions = heldItem.getVersionDetails();
		assertThat(versions).isNotNull().isNotEmpty();

		Optional<HeldItemVersionResponseSoap> versionFound = versions.stream()
		  .filter(v -> {
			  ResourceResponseSoap versionRes = v.getVersion();
			  return versionRes != null && expectedVersionName.equalsIgnoreCase(versionRes.getName());
		  })
		  .findFirst();

		assertThat(versionFound).as("expected version %s for item %s", expectedVersionName, expectedItemName)
		  .isPresent();

		HeldItemVersionResponseSoap v = versionFound.get();
		assertThat(v.getRarity()).as("rarity mismatch for version %s", expectedVersionName).isEqualTo(expectedRarity);

		ResourceResponseSoap versionRes = v.getVersion();
		assertNotNull(versionRes.getUrl(), "version.url must not be null");
		assertThat(versionRes.getUrl()).contains("/version/").endsWith("/");
	}

}
