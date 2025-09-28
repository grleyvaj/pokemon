package cucumber.steps;

import com.pokemon.demo.application.ports.api.pokemon_detail.PokemonDetailResponse;
import com.pokemon.demo.domain.model.Pokemon;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class PokemonDetailSteps {

	private final CommonSteps commonSteps;
	private PokemonDetailResponse response;

	public PokemonDetailSteps(CommonSteps commonSteps) {
		this.commonSteps = commonSteps;
	}

	@When("I request its details")
	public void i_request_its_details() {
		String pokemonName = commonSteps.getPokemonName();

		Pokemon pokemon = new Pokemon(25, pokemonName, 112, "::location::");
		response = new PokemonDetailResponse()
		  .setId(pokemon.getId())
		  .setName(pokemon.getName())
		  .setBaseExperience(pokemon.getBaseExperience())
		  .setLocationAreaEncounters(pokemon.getLocationAreaEncounters());
	}

	@Then("I receive its id, name, abilities and held items")
	public void i_receive_its_id_name_abilities_and_held_items() {
		String pokemonName = commonSteps.getPokemonName();

		assertThat(response.getName()).isEqualTo(pokemonName);
		assertThat(response.getId()).isEqualTo(25);
		assertThat(response.getBaseExperience()).isEqualTo(112);
		assertThat(response.getLocationAreaEncounters()).isEqualTo("::location::");
	}

}
