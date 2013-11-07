package com.dvlcube.service;

import com.dvlcube.droid.service.rr.AsyncRequest;

/**
 * 
 * @author wonka
 * @since 15/09/2012
 */
public interface AsyncCRUDService<T extends BasicInfo> extends CRUDService<T> {
	/**
	 * @return
	 * @author wonka
	 * @since 19/03/2013
	 */
	Object getLock();

	/**
	 * @param date
	 * @return
	 * @author wonka
	 * @since 19/03/2013
	 */
	boolean hasUpdates(long date);

	/**
	 * Blocks execution of the current thread until the service decides there are updates.
	 * 
	 * @author wonka
	 * @throws InterruptedException
	 * @since 06/11/2013
	 */
	void waitForUpdates(AsyncRequest request) throws InterruptedException;
}