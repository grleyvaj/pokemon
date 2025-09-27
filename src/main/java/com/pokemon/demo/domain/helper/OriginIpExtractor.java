package com.pokemon.demo.domain.helper;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

public class OriginIpExtractor {

	private OriginIpExtractor() {
		throw new UnsupportedOperationException("Utility class");
	}

	public static String ip() {
		Optional<HttpServletRequest> reqOpt = Optional.empty();
		var attrs = RequestContextHolder.getRequestAttributes();
		if(attrs instanceof ServletRequestAttributes servletAttrs) {
			reqOpt = Optional.of(servletAttrs.getRequest());
		}

		return reqOpt.map(HttpServletRequest::getRemoteAddr).orElse("unknown");
	}

}
