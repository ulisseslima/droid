package com.dvlcube.droid.service;

import com.dvlcube.droid.bean.Listing;
import com.dvlcube.service.CRUDService;
import com.dvlcube.service.Response;

/**
 * 
 * @author wonka
 * @since 27/03/2013
 */
public interface ListingService extends CRUDService<Listing> {

	/**
	 * @param listing
	 * @param name
	 * @return
	 * @author wonka
	 * @since 28/03/2013
	 */
	Response<Listing> addOrUpdate(Listing listing, String name);
}
