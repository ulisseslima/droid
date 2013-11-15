package com.dvlcube.droid.servlet;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dvlcube.cuber.CubeList;
import com.dvlcube.cuber.CubeList.Factory;
import com.dvlcube.cuber.Debug;
import com.dvlcube.droid.bean.User;
import com.dvlcube.droid.service.UserService;
import com.dvlcube.droid.service.rr.UpdateLocationRequest;
import com.dvlcube.service.Response;

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
			return service.list(null, start, maxResults).getContents();
		}
	});

	@Autowired
	private UserService service;

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

	@RequestMapping("/updateLocation")
	public @ResponseBody
	Response<User> updateLocation(final UpdateLocationRequest request) {
		service.updateLocation(request);
		return new Response<>(true);
	}

	// @RequestMapping("/updatePeople")
	// public @ResponseBody
	// DeferredResult<Response<User>> updatePeople(final UpdateLocationRequest request) {
	// Debug.log("Receiving people request");
	// final DeferredResult<Response<User>> deferredResult = new DeferredResult<>();
	// request.setUser(service.getSession());
	// new Thread(new Runnable() {
	//
	// @Override
	// public void run() {
	// service.waitForUpdates(request);
	// Response<User> others = service.listUsersOnSamePage(request);
	// Debug.log("Servlet Found %d users on same page", others.getContents().size());
	// deferredResult.setResult(others);
	// }
	// }).start();
	// return deferredResult;
	// }

	@RequestMapping("/updatePeople")
	public @ResponseBody
	Response<User> updatePeople(final UpdateLocationRequest request) {
		Debug.log("Receiving people request");
		request.setUser(service.getSession());

		service.waitForUpdates(request);
		Response<User> others = service.listUsersOnSamePage(request);
		Debug.log("Servlet Found %d users on same page", others.getContents().size());
		return others;
	}
}
