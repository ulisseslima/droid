package com.dvlcube.session;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author wonka
 * @since 25/02/2013
 */
@Controller
public class LoginServlet {

	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public String denied(final ModelMap model) {
		return "denied";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(final ModelMap model) {
		return "login";
	}

	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginerror(final ModelMap model) {
		model.addAttribute("error", "true");
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(final ModelMap model) {
		return "login";
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String printWelcome(final ModelMap model, final Principal principal) {
		final String name = principal.getName();
		model.addAttribute("username", name);
		model.addAttribute("message", "Spring Security Custom Form example");
		return "hello";
	}
}
