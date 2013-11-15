package com.dvlcube.component;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dvlcube.service.Response;

@Component
public class I18nInterceptor extends HandlerInterceptorAdapter implements MessageSourceAware {

	private MessageSource messageSource;

	@Override
	public void postHandle(
		final HttpServletRequest request,
		final HttpServletResponse response,
		final Object handler,
		final ModelAndView modelAndView) throws Exception {
		final Enumeration<String> attributeNames = request.getAttributeNames();
		while (attributeNames.hasMoreElements()) {
			final String name = attributeNames.nextElement();
			final Object attribute = request.getAttribute(name);

			if (attribute instanceof Response) {
				final Response<?> cubeResponse = (Response<?>) attribute;
				final String key = cubeResponse.getMessage();
				if (key != null) {
					final String message = messageSource.getMessage(key, null, null, request.getLocale());
					cubeResponse.setMessage(message);
				}
			}
		}
	}

	/**
	 * @param messageSource
	 *            the messageSource to set
	 */
	@Override
	public void setMessageSource(final MessageSource messageSource) {
		this.messageSource = messageSource;
	}
}
