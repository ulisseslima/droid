package com.dvlcube.droid.service;

import com.dvlcube.droid.bean.User;
import com.dvlcube.droid.service.rr.NewUserRequest;
import com.dvlcube.droid.service.rr.UpdateLocationRequest;
import com.dvlcube.service.AsyncCRUDService;
import com.dvlcube.service.Response;

/**
 * 
 * @author wonka
 * @since 10/09/2012
 */
public interface UserService extends AsyncCRUDService<User> {

	/**
	 * @param userRequest
	 * @return The Response of trying to add a new user.
	 * @author wonka
	 * @since 24/03/2013
	 */
	Response<User> addUser(NewUserRequest userRequest);

	/**
	 * @return the currently logged in user.
	 * @author wonka
	 * @since 27/10/2013
	 */
	User getSession();

	/**
	 * @return the id of the logged in user.
	 * @author wonka
	 * @since 28/10/2013
	 */
	Long getSessionId();

	/**
	 * @param request
	 * @return online users on the same page.
	 * @author wonka
	 * @since 26/10/2013
	 */
	Response<User> listUsersOnSamePage(UpdateLocationRequest request);

	/**
	 * @param isOnline
	 * @author wonka
	 * @since 28/10/2013
	 */
	void setOnlineStatus(boolean isOnline);

	/**
	 * @param request
	 * @return the user whose attributes have been updated.
	 * @author wonka
	 * @since 26/10/2013
	 */
	User updateLocation(UpdateLocationRequest request);
}
