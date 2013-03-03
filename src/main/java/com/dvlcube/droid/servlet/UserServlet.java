package com.dvlcube.droid.servlet;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dvlcube.droid.bean.User;
import com.dvlcube.droid.service.UserService;
import com.dvlcube.service.Response;
import com.dvlcube.util.CubeList;
import com.dvlcube.util.CubeList.Factory;

/**
 * 
 * @author wonka
 * @since 23/02/2013
 */
@Controller
@RequestMapping("/user")
public class UserServlet {
	private static final String _namespace = "user/user_";
	private static final String index = _namespace + "index";

	private final CubeList<User> list = new CubeList<>(new Factory<User>() {
		@Override
		public List<User> f(final int start, final int maxResults) {
			return service.list(start, maxResults).getContents();
		}
	});

	@Autowired
	private UserService service;

	@RequestMapping("/add")
	public @ResponseBody
	Response<User> add(final User user) {
		return service.add(user);
	}

	@RequestMapping("/get")
	public @ResponseBody
	Response<User> get(final long id) {
		return service.get(id);
	}

	@RequestMapping("/")
	public String index(final Map<String, Object> map) {
		final Response<User> response = new Response<>(true, list);
		map.put("response", response);
		return index;
	}

	@RequestMapping("/scroll")
	public @ResponseBody
	Response<User> scroll() {
		final Response<User> response = new Response<>(true, list.scroll());
		return response;
	}
}
