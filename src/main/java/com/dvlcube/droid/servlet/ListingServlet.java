package com.dvlcube.droid.servlet;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dvlcube.droid.bean.Listing;
import com.dvlcube.droid.service.ListingService;
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
	private ListingService service;

	@RequestMapping("/add")
	public @ResponseBody
	Response<Listing> add(Principal principal, final Listing listing) {
		if (listing.hasRequiredAttributes()) {
			final Response<Listing> savedListing = service.addOrUpdate(listing, principal.getName());
			return savedListing;
		} else {
			return null;
		}
	}

	@RequestMapping("/")
	public String index(final Map<String, Object> map) {
		final Response<Listing> response = service.list();
		map.put("response", response);
		return index;
	}
}
