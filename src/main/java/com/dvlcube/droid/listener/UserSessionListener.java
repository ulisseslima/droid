package com.dvlcube.droid.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.dvlcube.droid.service.UserService;

/**
 * @author wonka
 * @since 27/10/2013
 */
public class UserSessionListener implements HttpSessionListener {

	class SessionContext {
		protected String userName;
		protected UserService userService;
	}

	private SessionContext getSessionContext(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
		UserService userService = (UserService) ctx.getBean("userService");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		SessionContext context = new SessionContext();
		context.userService = userService;
		context.userName = auth.getName();

		return context;
	}

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		SessionContext context = getSessionContext(event);

		context.userService.setOnlineStatus(context.userName, true);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		SessionContext context = getSessionContext(event);

		context.userService.setOnlineStatus(context.userName, false);
	}
}
