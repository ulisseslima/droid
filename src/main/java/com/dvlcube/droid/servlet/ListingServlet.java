package com.dvlcube.droid.servlet;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dvlcube.cuber.Debug;
import com.dvlcube.droid.bean.Event;
import com.dvlcube.droid.bean.Listing;
import com.dvlcube.droid.bean.User;
import com.dvlcube.droid.service.EventService;
import com.dvlcube.droid.service.ListingService;
import com.dvlcube.droid.service.UserService;
import com.dvlcube.droid.service.rr.NewEventsRequest;
import com.dvlcube.droid.service.rr.NewParticipantRequest;
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
	private EventService events;

	@Autowired
	private ListingService listings;

	@Autowired
	private UserService users;

	@RequestMapping("/add")
	public @ResponseBody
	Response<Listing> add(@RequestBody final Listing listing) {
		if (listing.hasRequiredAttributes()) {
			return listings.addOrUpdate(listing);
		} else {
			return null;
		}
	}

	@RequestMapping("/addevent")
	public @ResponseBody
	Response<Event> addevent(@RequestBody final Event event) {
		if (event.hasRequiredAttributes()) {
			final Response<Event> savedEvent = events.addOrUpdate(event);
			return savedEvent;
		} else {
			return null;
		}
	}

	@RequestMapping("/addparticipant")
	public @ResponseBody
	Response<User> addparticipant(@RequestBody final NewParticipantRequest participant) {
		return listings.addParticipant(participant);
	}

	@RequestMapping("/")
	public String index(final Map<String, Object> map) {
		final Response<Listing> response = listings.listRecentFirst();
		map.put("response", response);
		return index;
	}

	@RequestMapping("/{listingId}")
	public String listing(@PathVariable final long listingId, final Map<String, Object> map) {
		User me = users.getSession();
		Listing listing = listings.get(listingId).getContent();
		final Response<Event> response = events.listByListing(listingId);

		map.put("me", me);
		map.put("listing", listing);
		map.put("response", response);

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
	// @RequestMapping("/refresh")
	// public @ResponseBody
	// DeferredResult<Response<Event>> refresh(final NewEventsRequest request) {
	// Debug.log("Receiving events request");
	// final DeferredResult<Response<Event>> deferredResult = new DeferredResult<>();
	// request.setUser(users.getSession());
	// new Thread(new Runnable() {
	//
	// @Override
	// public void run() {
	// events.waitForUpdates(request);
	// Response<Event> updatedEvents = events.listNew(request);
	// deferredResult.setResult(updatedEvents);
	// }
	// }).start();
	// return deferredResult;
	// }

	@RequestMapping("/refresh")
	public @ResponseBody
	Response<Event> refresh(final NewEventsRequest request) {
		Debug.log("Receiving events request");
		request.setUser(users.getSession());
		events.waitForUpdates(request);
		Response<Event> updatedEvents = events.listNew(request);
		return updatedEvents;
	}

	@RequestMapping("/removeevent")
	public @ResponseBody
	Response<Event> removeevent(@RequestBody final Event event) {
		if (event.hasRequiredAttributes()) {
			final Response<Event> deletedEvent = events.delete(event);
			return deletedEvent;
		} else {
			return null;
		}
	}
}
