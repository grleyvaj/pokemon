package com.pokemon.demo.domain.helper;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OriginIpExtractorTest {

	@AfterEach
	void tearDown() {
		RequestContextHolder.resetRequestAttributes();
	}

	@Test
	void test_when_private_constructor_then_throws_exception() throws Exception {
		var constructor = OriginIpExtractor.class.getDeclaredConstructor();
		constructor.setAccessible(true);

		assertThatThrownBy(constructor::newInstance)
		  .isInstanceOf(java.lang.reflect.InvocationTargetException.class)
		  .hasCauseInstanceOf(UnsupportedOperationException.class)
		  .hasRootCauseMessage("Utility class");
	}

	@Test
	void test_when_no_request_then_returns_unknown() {
		assertThat(OriginIpExtractor.ip()).isEqualTo("unknown");
	}

	@Test
	void test_when_request_present_then_returns_remote_addr() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRemoteAddr()).thenReturn("192.168.0.1");

		ServletRequestAttributes attrs = new ServletRequestAttributes(request);
		RequestContextHolder.setRequestAttributes(attrs);

		assertThat(OriginIpExtractor.ip()).isEqualTo("192.168.0.1");
	}

}