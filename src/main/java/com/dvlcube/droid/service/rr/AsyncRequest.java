package com.dvlcube.droid.service.rr;

import com.dvlcube.droid.bean.User;

/**
 * @author wonka
 * @since 05/11/2013
 */
public class AsyncRequest {
	private long lastUpdate;
	private User user;

	/**
	 * @return the lastUpdate
	 */
	public long getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param lastUpdate
	 *            the lastUpdate to set
	 */
	public void setLastUpdate(long lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
}
