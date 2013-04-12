package com.dvlcube.droid.service;

import com.dvlcube.droid.bean.Listing;
import com.dvlcube.droid.bean.User;
import com.dvlcube.droid.service.rr.NewParticipantRequest;
import com.dvlcube.service.CRUDService;
import com.dvlcube.service.Response;

/**
 * 
 * @author wonka
 * @since 27/03/2013
 */
public interface ListingService extends CRUDService<Listing> {

	/**
	 * @param participant
	 * @return
	 * @author wonka
	 * @since 10/04/2013
	 */
	Response<User> addParticipant(NewParticipantRequest participant);
}
