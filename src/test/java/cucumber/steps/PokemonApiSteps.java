package cucumber.steps;

import com.pokemon.demo.application.ports.api.pokemon_detail.PokemonDetailResponse;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PokemonApiSteps {

	private PokemonDetailResponse response;

	@LocalServerPort
	private int port;

	private final CommonSteps commonSteps;

	public PokemonApiSteps(CommonSteps commonSteps) {
		this.commonSteps = commonSteps;
	}

	@Before
	public void setup() {
		io.restassured.RestAssured.port = port;
	}

	@When("I request Pokemon details for {string}")
	public void i_call_get_api(String name) {
		Response resp = given()
		  .when()
		  .get("/api/pokemons/{name}", name)
		  .then()
		  .statusCode(200)
		  .extract().response();

		response = resp.as(PokemonDetailResponse.class);
	}

	@Then("I receive its id, name and base experience")
	public void i_receive_basic_fields() {
		String pokemonName = commonSteps.getPokemonName();

		assertThat(response.getName()).isEqualTo(pokemonName);
		assertNotNull(response.getId());
		assertThat(response.getBaseExperience()).isNotNull();
	}

}
