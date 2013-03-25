package com.dvlcube.droid.service;

import com.dvlcube.droid.bean.User;
import com.dvlcube.droid.service.rr.NewUserRequest;
import com.dvlcube.service.CRUDService;
import com.dvlcube.service.Response;

/**
 * 
 * @author wonka
 * @since 10/09/2012
 */
public interface UserService extends CRUDService<User> {

	/**
	 * @param userRequest
	 * @return The Response of trying to add a new user.
	 * @author wonka
	 * @since 24/03/2013
	 */
	Response<User> newUser(NewUserRequest userRequest);
}
