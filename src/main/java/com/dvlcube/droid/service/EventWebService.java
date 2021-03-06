package com.dvlcube.droid.service;

import java.util.Date;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dvlcube.dao.CubeOrder;
import com.dvlcube.droid.bean.Event;
import com.dvlcube.droid.dao.EventDao;
import com.dvlcube.droid.service.rr.NewEventsRequest;
import com.dvlcube.service.Response;
import com.dvlcube.service.ServiceTemplate;

/**
 * 
 * @author wonka
 * @since 10/09/2012
 */
@Service
public class EventWebService extends ServiceTemplate<Event> implements EventService {

	@Autowired
	private EventDao dao;

	@Override
	protected EventDao getDao() {
		return dao;
	}

	@Override
	protected Class<Event> getT() {
		return Event.class;
	}

	@Override
	public Response<Event> listByListing(long listingId) {
		Criterion matchingListingId = Restrictions.eq(Event.Field.parent + ".id", listingId);
		return list(null, CubeOrder.desc(Event.Field.priority), matchingListingId);
	}

	@Override
	public Response<Event> listByPriority() {
		return list(null, CubeOrder.desc(Event.Field.priority));
	}

	@Override
	public Response<Event> listNew(final NewEventsRequest request) {
		Date date = new Date(request.getLastUpdate());
		SimpleExpression recent = Restrictions.ge(Event.Field.dateModified.name(), date);
		Criterion matchingTitle = Restrictions.ne(Event.Field.name.name(), request.getFocusedEventName());
		Criterion matchingListingId = Restrictions.eq(Event.Field.parent.id(), request.getParentId());
		return list(request, recent, matchingTitle, matchingListingId);
	}
}
