package com.dvlcube.droid.security;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dvlcube.droid.bean.User;
import com.dvlcube.droid.service.UserService;
import com.dvlcube.droid.service.rr.NewUserRequest;
import com.dvlcube.service.Response;

/**
 * 
 * @author wonka
 * @since 25/02/2013
 */
@Controller
public class GatekeeperServlet {

	@Autowired
	private UserService service;

	@RequestMapping("/denied")
	public String denied(final ModelMap model) {
		return "denied";
	}

	/**
	 * @param model
	 * @param principal
	 * @return
	 * @author wonka
	 * @since 29/03/2013
	 */
	private String handleEntryPoint(final ModelMap model, final Principal principal) {
		if (principal != null) {
			final String name = principal.getName();
			model.addAttribute("username", name);
		}
		return "welcome";
	}

	@RequestMapping("/login")
	public String login(final ModelMap model) {
		return "login";
	}

	@RequestMapping("/loginfailed")
	public String loginfailed(final ModelMap model) {
		model.addAttribute("error", "true");
		return "login";
	}

	@RequestMapping("/logout")
	public String logout(final ModelMap model) {
		return "login";
	}

	@RequestMapping("/newuser")
	public String newUser(final ModelMap model, final NewUserRequest userRequest) {
		Response<User> response = service.addUser(userRequest);
		if (response.isSuccess()) {
			User user = response.getContent();
			model.addAttribute("username", user.getName());
		} else {
			model.addAttribute("userAlreadyExists", "true");
		}
		return "login";
	}

	@RequestMapping("/")
	public String root(final ModelMap model, final Principal principal) {
		return handleEntryPoint(model, principal);
	}

	@RequestMapping("/signup")
	public String signup(final ModelMap model) {
		return "signup";
	}

	@RequestMapping("/welcome")
	public String welcome(final ModelMap model, final Principal principal) {
		return handleEntryPoint(model, principal);
	}
}
