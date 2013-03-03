package com.dvlcube.droid.service;

import com.dvlcube.droid.bean.Event;
import com.dvlcube.service.Response;
import com.dvlcube.service.ServiceCRUD;

/**
 * 
 * @author wonka
 * @since 10/09/2012
 */
public interface EventService extends ServiceCRUD<Event> {

	/**
	 * @return All Events ordered by priority.
	 * @author wonka
	 * @since 02/03/2013
	 */
	Response<Event> listByPriority();
}
