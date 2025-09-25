package com.pokemon.demo.application.ports.api;

import com.pokemon.demo.domain.helper.Mapper;
import com.pokemon.demo.application.ports.api.response.PokemonDetailResponse;
import com.pokemon.demo.domain.exception.PokemonClientApiException;
import com.pokemon.demo.domain.exception.ResourceNotFoundException;
import com.pokemon.demo.domain.model.Pokemon;
import com.pokemon.demo.domain.repository.RequestLogCreateInput;
import com.pokemon.demo.domain.use_case.pokemon.PokemonDetailUseCase;
import com.pokemon.demo.domain.use_case.request_log.RequestLogCreateUseCase;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pokemons")
public class PokemonsApiController implements PokemonsApi {

	private final PokemonDetailUseCase pokemonDetailUseCase;
	private final RequestLogCreateUseCase requestLogCreateUseCase;
	private final Mapper<Pokemon, PokemonDetailResponse> pokemonDetailResponseMapper;

	@Override
	@GetMapping(value = "/{name}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<PokemonDetailResponse> detail(
	  @PathVariable(value = "name") String name,
	  HttpServletRequest request
	) throws ResourceNotFoundException, PokemonClientApiException {
		long startTime = System.currentTimeMillis();

		Pokemon pokemon = this.pokemonDetailUseCase.execute(name);
		PokemonDetailResponse response = this.pokemonDetailResponseMapper.map(pokemon);

		RequestLogCreateInput requestLogCreateInput = new RequestLogCreateInput(
		  request.getRemoteAddr(),
		  LocalDateTime.now(),
		  request.getMethod() + " " + request.getRequestURI()
		)
		  .setDurationMs(System.currentTimeMillis() - startTime)
		  .setResponseObj(response);
		this.requestLogCreateUseCase.execute(requestLogCreateInput);

		return ResponseEntity.ok(response);
	}

}
