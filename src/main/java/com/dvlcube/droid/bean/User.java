package com.dvlcube.droid.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.dvlcube.bean.Identifiable;
import com.dvlcube.reflection.FieldName;

/**
 * @author wonka
 * @since 07/08/2012
 */
@Entity
public class User implements Identifiable {
	public enum Field implements FieldName {
		birth, email, events, id, name, telephone
	}

	private static final long serialVersionUID = 3262522508885048363L;
	private Date birth; // datetime
	private String email;
	@OneToMany
	private List<Event> events;
	@Id
	@GeneratedValue
	private Long id; // id
	private String name;
	private int telephone;

	public User() {

	}

	public User(final String name) {
		this.name = name;
	}

	/**
	 * @return the birth
	 */
	public Date getBirth() {
		return birth;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the events
	 */
	public List<Event> getEvents() {
		return events;
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
		return name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the telephone
	 */
	public int getTelephone() {
		return telephone;
	}

	/**
	 * @param birth
	 *            the birth to set
	 */
	public void setBirth(final Date birth) {
		this.birth = birth;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(final String email) {
		this.email = email;
	}

	/**
	 * @param events
	 *            the events to set
	 */
	public void setEvents(final List<Event> events) {
		this.events = events;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	@Override
	public void setLabel(final String label) {
		name = label;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @param telephone
	 *            the telephone to set
	 */
	public void setTelephone(final int telephone) {
		this.telephone = telephone;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
}
