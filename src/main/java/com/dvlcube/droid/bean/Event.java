package com.dvlcube.droid.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
public class Event implements BasicInfo {
	public enum Field implements FieldName {
		creator, dateEnd, dateModified, dateStart, description, done, guests, id, priority, reminders, repetition, title
	}

	private static final long serialVersionUID = 6362154457938757910L;
	@ManyToOne
	private User creator; // ref_many2one #not null
	private Date dateEnd; // timestamp
	private Date dateModified; // timestamp
	private Date dateStart; // timestamp
	private String description;
	private boolean done;
	// OneToMany private List<User> guests; // ref_one2many
	@Id
	@GeneratedValue
	private Long id; // id
	private int priority;
	// OneToMany private List<Reminder> reminders;
	private Repetition repetition; // String
	private String title;

	/**
	 * @return the creator
	 */
	public User getCreator() {
		return creator;
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
		return StringUtils.escapeHTML(getTitle());
	}

	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * @return the repetition
	 */
	public Repetition getRepetition() {
		return repetition;
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
		if (StringUtils.isBlank(title)) {
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
	 * @param creator
	 *            the creator to set
	 */
	public void setCreator(final User creator) {
		this.creator = creator;
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
	 * @param priority
	 *            the priority to set
	 */
	public void setPriority(final int priority) {
		this.priority = priority;
	}

	/**
	 * @param repetition
	 *            the repetition to set
	 */
	public void setRepetition(final Repetition repetition) {
		this.repetition = repetition;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	@Override
	public void setTitle(final String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", title=" + title + ", priority=" + priority + ", description="
				+ description + "]";
	}

	@Override
	public void touch() {
		dateModified = new Date();
	}
}
