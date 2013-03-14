package com.dvlcube.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

/**
 * http://stackoverflow.com/questions/3339431/how-to-handle-expired-session-using-spring-security-and-jquery
 * 
 * @author wonka
 * @since 12/03/2013
 */
public class AjaxAwareAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

	public AjaxAwareAuthenticationEntryPoint(final String loginUrl) {
		super(loginUrl);
	}

	@Override
	public void commence(
		final HttpServletRequest request,
		final HttpServletResponse response,
		final AuthenticationException authException) throws IOException, ServletException {

		final boolean isAjax = request.getRequestURI().startsWith("/api/secured");

		if (isAjax) {
			response.sendError(403, "Forbidden");
		} else {
			super.commence(request, response, authException);
		}
	}
}