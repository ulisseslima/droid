package com.dvlcube.droid.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dvlcube.droid.bean.Listing;
import com.dvlcube.droid.bean.User;
import com.dvlcube.droid.dao.ListingDao;
import com.dvlcube.droid.dao.UserDao;
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
	public Response<Listing> addOrUpdate(Listing listing, String name) {
		User user = userDao.retrieve(User.class, new User(name));
		listing.setCreator(user);
		Response<Listing> addOrUpdate = addOrUpdate(listing);
		return addOrUpdate;
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