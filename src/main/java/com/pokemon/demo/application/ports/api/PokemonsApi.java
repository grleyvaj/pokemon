package com.pokemon.demo.application.ports.api;

import com.pokemon.demo.application.handlers.api.response.ErrorResponse;
import com.pokemon.demo.application.ports.api.pokemon_detail.PokemonDetailResponse;
import com.pokemon.demo.domain.exception.PokemonClientApiException;
import com.pokemon.demo.domain.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "${api.tag.pokemon.name}", description = "${api.tag.pokemon.description}")
public interface PokemonsApi {

	@Operation(
	  summary = "${api.pokemon.detail.summary}",
	  description = "${api.pokemon.detail.description}",
	  operationId = "detailPokemon"
	)
	@ApiResponse(responseCode = "200", description = "${api.pokemon.detail.response.200.description}",
	  content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = PokemonDetailResponse.class)))
	@ApiResponse(responseCode = "401", description = "${api.response.401.description}", content = @Content)
	@ApiResponse(responseCode = "403", description = "${api.response.403.description}", content = @Content)
	@ApiResponse(responseCode = "404", description = "${api.response.404.description}",
	  content = @Content(
		schema = @Schema(implementation = ErrorResponse.class),
		examples = @ExampleObject(
		  value = "{\"message\": \"Pokemon with name 'missingno' not found\"}"
		)
	  ))
	@ApiResponse(responseCode = "500", description = "${api.response.500.description}",
	  content = @Content(
		schema = @Schema(implementation = ErrorResponse.class),
		examples = @ExampleObject(
		  value = "{\"message\": \"Error calling Pokemon API. Status: 500\"}"
		)
	  ))
	ResponseEntity<PokemonDetailResponse> detail(
	  @Parameter(
		in = ParameterIn.PATH,
		name = "name",
		description = "${pokemon.name.description}",
		required = true,
		schema = @Schema(type = "string", format = "String", example = "butterfree")
	  ) String name,
	  HttpServletRequest request
	) throws ResourceNotFoundException, PokemonClientApiException;

}
