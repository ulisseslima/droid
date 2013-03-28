package com.dvlcube.bean;

import java.util.Date;

/**
 * 
 * @author wonka
 * @since 12/03/2013
 */
public interface Trackable {
	Date getDateModified();

	void setDateModified(Date date);

	/**
	 * When touched, a Trackable object should update its relevant properties.
	 * 
	 * @author wonka
	 * @since 27/03/2013
	 */
	void touch();
}
