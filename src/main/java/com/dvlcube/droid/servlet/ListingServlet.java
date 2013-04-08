package com.dvlcube.droid.servlet;

import java.util.Map;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dvlcube.droid.bean.Event;
import com.dvlcube.droid.bean.Listing;
import com.dvlcube.droid.service.EventService;
import com.dvlcube.droid.service.ListingService;
import com.dvlcube.droid.service.rr.NewEventsRequest;
import com.dvlcube.service.Response;

/**
 * 
 * @author wonka
 * @since 27/03/2013
 */
@Controller
@RequestMapping("/listing")
public class ListingServlet {
	private static final String _namespace = "listing/listing_";
	private static final String index = _namespace + "index";

	@Autowired
	private EventService eventService;

	@Autowired
	private ListingService service;

	@RequestMapping("/add")
	public @ResponseBody
	Response<Listing> add(@RequestBody final Listing listing) {
		if (listing.hasRequiredAttributes()) {
			return service.addOrUpdate(listing);
		} else {
			return null;
		}
	}

	@RequestMapping("/addevent")
	public @ResponseBody
	Response<Event> addevent(@RequestBody final Event event) {
		if (event.hasRequiredAttributes()) {
			final Response<Event> savedEvent = eventService.addOrUpdate(event);
			return savedEvent;
		} else {
			return null;
		}
	}

	@RequestMapping("/")
	public String index(final Map<String, Object> map) {
		final Response<Listing> response = service.listRecentFirst();
		map.put("response", response);
		return index;
	}

	@RequestMapping("/{listingId}")
	public String listing(@PathVariable final long listingId, final Map<String, Object> map) {
		final Response<Event> response = eventService.listByListing(listingId);
		Listing listing = service.get(listingId).getContent();
		map.put("response", response);
		map.put("listing", listing);
		return "event/event_index";
	}

	/**
	 * Long polled.
	 * <p>
	 * Will return a list of new entries as soon as new entries become available.
	 * 
	 * @param request
	 *            request for new events.
	 * @return A list with new entries since the last client update.
	 * @author wonka
	 * @since 19/03/2013
	 */
	@RequestMapping("/refresh")
	public @ResponseBody
	Callable<Response<Event>> refresh(final NewEventsRequest request) {
		return new Callable<Response<Event>>() {
			@Override
			public Response<Event> call() throws Exception {
				synchronized (eventService.getLock()) {
					while (!eventService.hasUpdates(request.getLastUpdate())) {
						eventService.getLock().wait();
					}
					final Response<Event> updatedEvents = eventService.listNew(request);
					return updatedEvents;
				}
			}
		};
	}

	@RequestMapping("/share")
	public @ResponseBody
	Response<Listing> share(@RequestBody final Listing listing) {
		// get the list of users and add it to this listing
		return null;
	}
}
