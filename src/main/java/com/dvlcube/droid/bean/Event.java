package com.dvlcube.droid.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.dvlcube.bean.Child;
import com.dvlcube.bean.Repetition;
import com.dvlcube.reflection.FieldName;
import com.dvlcube.service.BasicInfo;
import com.dvlcube.util.StringUtils;

/**
 * 
 * @author wonka
 * @since 23/02/2013
 */
@Entity
public class Event implements BasicInfo, Owned, Child<Listing> {
	public enum Field implements FieldName {
		dateEnd, dateModified, dateStart, description, done, guests, id, name, owner, parent, priority, reminders, repetition;
		public String id() {
			return name() + ".id";
		}
	}

	private static final long serialVersionUID = 6362154457938757910L;
	private Date dateEnd; // timestamp
	private Date dateModified; // timestamp
	private Date dateStart; // timestamp
	private String description;
	private boolean done;
	@Id
	@GeneratedValue
	private Long id; // id
	private String name;
	@ManyToOne
	private User owner; // ref_many2one #not null
	@ManyToOne
	private Listing parent;
	private Integer priority;
	// OneToMany private List<Reminder> reminders;
	private Repetition repetition; // String

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
		Event other = (Event) obj;
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
		if (parent == null) {
			if (other.parent != null) {
				return false;
			}
		} else if (!parent.equals(other.parent)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the dateEnd
	 */
	public Date getDateEnd() {
		return dateEnd;
	}

	/**
	 * @return the dateModified
	 */
	@Override
	public Date getDateModified() {
		return dateModified;
	}

	/**
	 * @return the dateStart
	 */
	public Date getDateStart() {
		return dateStart;
	}

	/**
	 * @return the description
	 */
	@Override
	public String getDescription() {
		return description;
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
		return StringUtils.escapeHTML(getName());
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
	public Listing getParent() {
		return parent;
	}

	/**
	 * @return the priority
	 */
	public Integer getPriority() {
		return priority;
	}

	/**
	 * @return the repetition
	 */
	public Repetition getRepetition() {
		return repetition;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (description == null ? 0 : description.hashCode());
		result = prime * result + (name == null ? 0 : name.hashCode());
		result = prime * result + (owner == null ? 0 : owner.hashCode());
		result = prime * result + (parent == null ? 0 : parent.hashCode());
		return result;
	}

	@Override
	public boolean hasRequiredAttributes() {
		if (StringUtils.isBlank(name)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the done
	 */
	public boolean isDone() {
		return done;
	}

	/**
	 * @param dateEnd
	 *            the dateEnd to set
	 */
	public void setDateEnd(final Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	/**
	 * @param dateModified
	 *            the dateModified to set
	 */
	@Override
	public void setDateModified(final Date dateModified) {
		this.dateModified = dateModified;
	}

	/**
	 * @param dateStart
	 *            the dateStart to set
	 */
	public void setDateStart(final Date dateStart) {
		this.dateStart = dateStart;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	@Override
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @param done
	 *            the done to set
	 */
	public void setDone(final boolean done) {
		this.done = done;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	@Override
	public void setId(final Long id) {
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
	public void setOwner(final User owner) {
		this.owner = owner;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	@Override
	public void setParent(Listing parent) {
		this.parent = parent;
	}

	/**
	 * @param priority
	 *            the priority to set
	 */
	public void setPriority(final Integer priority) {
		this.priority = priority;
	}

	/**
	 * @param repetition
	 *            the repetition to set
	 */
	public void setRepetition(final Repetition repetition) {
		this.repetition = repetition;
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
