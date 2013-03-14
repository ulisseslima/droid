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
	private Date sendDate; // timestamp

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the sendDate
	 */
	public Date getSendDate() {
		return sendDate;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @param sendDate
	 *            the sendDate to set
	 */
	public void setSendDate(final Date sendDate) {
		this.sendDate = sendDate;
	}
}
