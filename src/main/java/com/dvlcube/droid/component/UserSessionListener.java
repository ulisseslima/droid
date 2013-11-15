package com.dvlcube.droid.component;

import static com.dvlcube.cuber.Cuber.$;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.dvlcube.droid.service.UserService;
import com.dvlcube.droid.service.UserWebService;

/**
 * @author wonka
 * @since 27/10/2013
 */
public class UserSessionListener implements HttpSessionListener {

	class SessionContext {
		protected UserService userService;
	}

	private SessionContext getSessionContext(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
		UserService userService = (UserService) ctx.getBean($(UserWebService.class).getBeanName());

		SessionContext context = new SessionContext();
		context.userService = userService;
		return context;
	}

	@Override
	public void sessionCreated(HttpSessionEvent event) {
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		SessionContext context = getSessionContext(event);
		context.userService.setOnlineStatus(false);
	}
}
