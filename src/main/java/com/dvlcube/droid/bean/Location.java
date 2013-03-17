package com.dvlcube.droid.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 
 * @author wonka
 * @since 23/02/2013
 */
@Entity
public class Location {
	private String address;
	private String description;
	private Date founded; // datetime
	@Id
	@GeneratedValue
	private Long id; // id
	private String image;
	private String title;
	private String url;

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the founded
	 */
	public Date getFounded() {
		return founded;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(final String address) {
		this.address = address;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @param founded
	 *            the founded to set
	 */
	public void setFounded(final Date founded) {
		this.founded = founded;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(final String image) {
		this.image = image;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(final String title) {
		this.title = title;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(final String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Location [description=" + description + ", title=" + title + ", url=" + url + "]";
	}
}
