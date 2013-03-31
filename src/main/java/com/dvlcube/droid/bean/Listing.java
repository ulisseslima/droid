package com.dvlcube.droid.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.dvlcube.bean.Privacy;
import com.dvlcube.reflection.FieldName;
import com.dvlcube.service.BasicInfo;
import com.dvlcube.util.StringUtils;

/**
 * 
 * @author wonka
 * @since 27/03/2013
 */
@Entity
public class Listing implements BasicInfo, Owned {
	public enum Field implements FieldName {
		dateModified, description, events, id, owner, participants, privacy, title
	}

	private static final long serialVersionUID = -3623848542561114979L;

	private Date dateModified;
	private String description;
	@OneToMany
	private List<Event> events;
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private User owner;
	@OneToMany
	private List<User> participants;
	private Privacy privacy; // String

	private String title;

	public Listing() {
	}

	/**
	 * @param id
	 * @author wonka
	 * @since 29/03/2013
	 */
	public Listing(Long id) {
		this.id = id;
	}

	/**
	 * @return the dateModified
	 */
	@Override
	public Date getDateModified() {
		return dateModified;
	}

	/**
	 * @return the description
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/**
	 * @return the events
	 */
	public List<Event> getEvents() {
		return events;
	}

	@Override
	public FieldName[] getFields() {
		return Field.values();
	}

	/**
	 * @return the id
	 */
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public String getLabel() {
		if (title == null) {
			return "";
		}
		return StringUtils.escapeHTML(title);
	}

	/**
	 * @return the owner
	 */
	@Override
	public User getOwner() {
		return owner;
	}

	/**
	 * @return the participants
	 */
	public List<User> getParticipants() {
		return participants;
	}

	/**
	 * @return the privacy
	 */
	public Privacy getPrivacy() {
		return privacy;
	}

	/**
	 * @return the title
	 */
	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public boolean hasRequiredAttributes() {
		if (title == null) {
			return false;
		}
		return true;
	}

	/**
	 * @param dateModified
	 *            the dateModified to set
	 */
	@Override
	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param events
	 *            the events to set
	 */
	public void setEvents(List<Event> events) {
		this.events = events;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param owner
	 *            the owner to set
	 */
	@Override
	public void setOwner(User owner) {
		this.owner = owner;
	}

	/**
	 * @param participants
	 *            the participants to set
	 */
	public void setParticipants(List<User> participants) {
		this.participants = participants;
	}

	/**
	 * @param privacy
	 *            the privacy to set
	 */
	public void setPrivacy(final Privacy privacy) {
		this.privacy = privacy;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return StringUtils.stringify(this);
	}

	@Override
	public void touch() {
		dateModified = new Date();
	}
}
