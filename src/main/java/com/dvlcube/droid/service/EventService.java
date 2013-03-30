package com.dvlcube.droid.service;

import com.dvlcube.droid.bean.Event;
import com.dvlcube.droid.service.rr.NewEventsRequest;
import com.dvlcube.service.AsyncCRUDService;
import com.dvlcube.service.Response;

/**
 * 
 * @author wonka
 * @since 10/09/2012
 */
public interface EventService extends AsyncCRUDService<Event> {

	/**
	 * @param listingId
	 * @return
	 * @author wonka
	 * @since 28/03/2013
	 */
	Response<Event> listByListing(long listingId);

	/**
	 * @return All Events ordered by priority.
	 * @author wonka
	 * @since 02/03/2013
	 */
	Response<Event> listByPriority();

	/**
	 * @param request
	 * @return
	 * @author wonka
	 * @since 13/03/2013
	 */
	Response<Event> listNew(NewEventsRequest request);
}
