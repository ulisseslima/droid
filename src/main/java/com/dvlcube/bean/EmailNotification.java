package com.dvlcube.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 
 * @author wonka
 * @since 24/02/2013
 */
@Entity
public class EmailNotification implements Notification {
	@Id
	@GeneratedValue
	private Long id; // id
	private Date when; // timestamp

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the when
	 */
	public Date getWhen() {
		return when;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @param when
	 *            the when to set
	 */
	public void setWhen(final Date when) {
		this.when = when;
	}
}
