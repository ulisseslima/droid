package com.dvlcube.droid.servlet;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dvlcube.droid.bean.Event;
import com.dvlcube.droid.service.EventService;
import com.dvlcube.service.Response;

/**
 * 
 * @author wonka
 * @since 23/02/2013
 */
@Controller
@RequestMapping("/event")
public class EventServlet {
	private static final String _namespace = "event/event_";
	private static final String index = _namespace + "index";

	@Autowired
	private EventService service;

	@RequestMapping("/add")
	public @ResponseBody
	Response<Event> add(final Event event) {
		if (event.hasRequiredAttributes()) {
			final Response<Event> savedEvent = service.addOrUpdate(event);
			return savedEvent;
		} else {
			return null;
		}
	}

	@RequestMapping("/")
	public String index(final Map<String, Object> map) {
		final Response<Event> response = service.listByPriority();
		map.put("response", response);
		return index;
	}
}
