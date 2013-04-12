package com.dvlcube.droid.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dvlcube.droid.bean.Listing;
import com.dvlcube.droid.bean.User;
import com.dvlcube.droid.dao.ListingDao;
import com.dvlcube.droid.dao.UserDao;
import com.dvlcube.droid.service.rr.NewParticipantRequest;
import com.dvlcube.service.Response;
import com.dvlcube.service.ServiceTemplate;

/**
 * 
 * @author wonka
 * @since 27/03/2013
 */
@Service
public class ListingWebService extends ServiceTemplate<Listing> implements ListingService {

	@Autowired
	private ListingDao dao;

	@Autowired
	private UserDao userDao;

	@Override
	public Response<User> addParticipant(NewParticipantRequest participant) {
		User user = userDao.retrieve(User.class, new User(participant.getName()));
		if (user != null) {
			Listing listing = dao.retrieve(getT(), participant.getListingId());
			Set<User> participants = listing.getParticipants();
			if (!participants.contains(user)) {
				participants.add(user);
				dao.update(listing);
				return new Response<>(true, user);
			}
		}
		return new Response<>(false);
	}

	@Override
	protected ListingDao getDao() {
		return dao;
	}

	@Override
	protected Class<Listing> getT() {
		return Listing.class;
	}
}
