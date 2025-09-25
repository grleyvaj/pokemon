package com.pokemon.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
  scanBasePackages = {
	"com.pokemon.demo.application.beans",
	"com.pokemon.demo.application.configuration",
	"com.pokemon.demo.application.ports",
	"com.pokemon.demo.application.handlers",
	"com.pokemon.demo.infrastructure.handlers",
	"com.pokemon.demo.infrastructure.persistence"
  }
)
public class PokemonDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokemonDemoApplication.class, args);
	}

}
