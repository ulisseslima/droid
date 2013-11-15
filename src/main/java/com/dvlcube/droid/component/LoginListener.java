package com.dvlcube.droid.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextListener;

import com.dvlcube.droid.service.UserService;

/**
 * @author wonka
 * @since 28/10/2013
 */
@Component
public class LoginListener extends RequestContextListener implements
		ApplicationListener<InteractiveAuthenticationSuccessEvent> {

	@Autowired
	private UserService users;

	@Override
	public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
		users.setOnlineStatus(true);
	}
}
