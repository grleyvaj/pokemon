package com.pokemon.demo.application.ports.api;

import com.pokemon.demo.application.ports.api.pokemon_detail.PokemonDetailResponse;
import com.pokemon.demo.domain.helper.Mapper;
import com.pokemon.demo.domain.model.Method;
import com.pokemon.demo.domain.model.Pokemon;
import com.pokemon.demo.domain.repository.RequestLogCreateInput;
import com.pokemon.demo.domain.use_case.pokemon.detail.PokemonDetailUseCase;
import com.pokemon.demo.domain.use_case.request_log.create.RequestLogCreateUseCase;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PokemonsApiControllerTest {

	private PokemonsApiController controller;

	@Mock
	private PokemonDetailUseCase pokemonDetailUseCase;

	@Mock
	private RequestLogCreateUseCase requestLogCreateUseCase;

	@Mock
	private Mapper<Pokemon, PokemonDetailResponse> pokemonDetailResponseMapper;

	@Mock
	private HttpServletRequest httpServletRequest;

	@BeforeEach
	void setUp() {
		this.controller = new PokemonsApiController(
		  this.pokemonDetailUseCase,
		  this.requestLogCreateUseCase,
		  this.pokemonDetailResponseMapper
		);
	}

	@Test
	void when_controller_is_called_then_returns_response_entity_ok() throws Exception {
		String name = "pikachu";
		Pokemon pokemon = new Pokemon(25, "Pikachu", 112, "Kanto");
		PokemonDetailResponse response = new PokemonDetailResponse().setId(25).setName("Pikachu");
		when(pokemonDetailUseCase.execute(name)).thenReturn(pokemon);
		when(pokemonDetailResponseMapper.map(pokemon)).thenReturn(response);
		when(httpServletRequest.getRemoteAddr()).thenReturn("127.0.0.1");

		ResponseEntity<PokemonDetailResponse> result = controller.detail(name, httpServletRequest);


		verify(pokemonDetailUseCase, times(1)).execute(name);
		verify(pokemonDetailResponseMapper, times(1)).map(pokemon);
		ArgumentCaptor<RequestLogCreateInput> captor = ArgumentCaptor.forClass(RequestLogCreateInput.class);
		verify(requestLogCreateUseCase).execute(captor.capture());
		assertThat(result).isNotNull();
		assertThat(result.getStatusCode().is2xxSuccessful()).isTrue();
		assertThat(result.getBody()).isEqualTo(response);
		RequestLogCreateInput logInput = captor.getValue();
		assertThat(logInput.getOriginIp()).isEqualTo("127.0.0.1");
		assertThat(logInput.getMethod()).isEqualTo(Method.DETAIL);
		assertThat(logInput.getResponseObj()).isEqualTo(response);
		assertTrue(logInput.getDurationMs().isPresent());
		assertTrue(logInput.getDurationMs().get() > 0);
	}

	@Test
	void when_pokemonDetailUseCase_throws_then_exception_is_propagated() throws Exception {
		String name = "mewtwo";
		when(this.pokemonDetailUseCase.execute(name)).thenThrow(new RuntimeException("boom!"));

		try {
			controller.detail(name, httpServletRequest);
		} catch (RuntimeException e) {
			assertThat(e).hasMessage("boom!");
		}

		verifyNoInteractions(pokemonDetailResponseMapper);
		verifyNoInteractions(requestLogCreateUseCase);
	}

}