package com.dvlcube.service;

/**
 * 
 * @author wonka
 * @since 15/09/2012
 */
public interface AsyncCRUDService<T extends BasicBean> extends CRUDService<T> {
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
}