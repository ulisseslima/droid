package com.dvlcube.droid.bean;

import static com.dvlcube.cuber.Cuber.$;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.dvlcube.bean.Privacy;
import com.dvlcube.reflection.FieldName;
import com.dvlcube.service.BasicInfo;

/**
 * 
 * @author wonka
 * @since 27/03/2013
 */
@Entity
public class Listing implements BasicInfo, Owned, Shared {
	public enum Field implements FieldName {
		dateModified, description, events, id, name, owner, participants, privacy
	}

	private static final long serialVersionUID = -3623848542561114979L;

	private Date dateModified;
	private String description;
	@OneToMany
	private Set<Event> events;
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	@ManyToOne
	private User owner;
	@OneToMany
	private Set<User> participants;

	private Privacy privacy; // String

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

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Listing other = (Listing) obj;
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (owner == null) {
			if (other.owner != null) {
				return false;
			}
		} else if (!owner.equals(other.owner)) {
			return false;
		}
		return true;
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
	public Set<Event> getEvents() {
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
		if (name == null) {
			return "";
		}
		return $(getName()).escapeHTML().string;
	}

	/**
	 * @return the name
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * @return the owner
	 */
	@Override
	public User getOwner() {
		return owner;
	}

	/**
	 * @return the parent
	 */
	@Override
	public Set<User> getParticipants() {
		return participants;
	}

	/**
	 * @return the privacy
	 */
	public Privacy getPrivacy() {
		return privacy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (description == null ? 0 : description.hashCode());
		result = prime * result + (name == null ? 0 : name.hashCode());
		result = prime * result + (owner == null ? 0 : owner.hashCode());
		return result;
	}

	@Override
	public boolean hasRequiredAttributes() {
		if (name == null) {
			return false;
		}
		return true;
	}

	public Criterion isSharedWith(String person) {
		Restrictions.or(Restrictions.eq("owner", owner), Restrictions.eq("parent", person));
		return null;
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
	public void setEvents(Set<Event> events) {
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
	 * @param name
	 *            the name to set
	 */
	@Override
	public void setName(String name) {
		this.name = name;
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
	 * @param parent
	 *            the parent to set
	 */
	@Override
	public void setParticipants(Set<User> participants) {
		this.participants = participants;
	}

	/**
	 * @param privacy
	 *            the privacy to set
	 */
	public void setPrivacy(final Privacy privacy) {
		this.privacy = privacy;
	}

	@Override
	public String toString() {
		return $(this).stringify();
	}

	@Override
	public void touch() {
		dateModified = new Date();
	}
}
