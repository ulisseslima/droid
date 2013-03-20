package com.dvlcube.droid.service;

import java.util.Date;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dvlcube.dao.CubeOrder;
import com.dvlcube.droid.bean.Event;
import com.dvlcube.droid.dao.EventDao;
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
	public Response<Event> listByPriority() {
		return list(CubeOrder.desc(Event.Field.priority));
	}

	@Override
	public Response<Event> listNew(final NewEventsRequest request) {
		System.out.println("listing ge " + request.getLastUpdate());
		return list(Restrictions.ge(Event.Field.dateModified.name(), new Date(request.getLastUpdate())),
				Restrictions.ne(Event.Field.title.name(), request.getFocusedEventTitle()));
	}
}
