package cucumber.steps;

import io.cucumber.java.en.Given;

public class CommonSteps {

	private String pokemonName;

	@Given("a Pokemon named {string}")
	public void a_pokemon_named(String name) {
		this.pokemonName = name;
	}

	public String getPokemonName() {
		return pokemonName;
	}

}
