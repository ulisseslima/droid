package com.dvlcube.droid.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 
 * @author wonka
 * @since 23/02/2013
 */
@Entity
public class Reminder {
	@Id
	@GeneratedValue
	private Long id; // id
	private Long minutes;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the minutes
	 */
	public long getMinutes() {
		return minutes;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @param minutes
	 *            the minutes to set
	 */
	public void setMinutes(final long minutes) {
		this.minutes = minutes;
	}

	@Override
	public String toString() {
		return "Reminder [id=" + id + ", minutes=" + minutes + "]";
	}

}
