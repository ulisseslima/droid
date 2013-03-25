package com.dvlcube.droid.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.dvlcube.droid.service.rr.NewUserRequest;
import com.dvlcube.reflection.FieldName;
import com.dvlcube.service.BasicInfo;

/**
 * @author wonka
 * @since 07/08/2012
 */
@Entity
public class User implements BasicInfo {
	public enum Field implements FieldName {
		birth, email, events, id, name, telephone
	}

	private static final long serialVersionUID = 3262522508885048363L;
	private Date birth; // datetime
	private Date dateModified; // timestamp
	private String email;
	private boolean enabled;
	@OneToMany
	private List<Event> events;
	@Id
	@GeneratedValue
	private Long id; // id
	private String name;
	private String password;
	private int telephone = 0;
	private String username;

	public User() {

	}

	/**
	 * @param userRequest
	 * @author wonka
	 * @since 24/03/2013
	 */
	public User(NewUserRequest userRequest) {
		name = username = userRequest.getUsername();
		password = userRequest.getPassword();
		telephone = 1234567890;
		enabled = true;
	}

	public User(final String name) {
		this.name = name;
		username = name;
	}

	/**
	 * @return the birth
	 */
	public Date getBirth() {
		return birth;
	}

	/**
	 * @return the dateModified
	 */
	@Override
	public Date getDateModified() {
		return dateModified;
	}

	@Override
	public String getDescription() {
		return toString();
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
		return name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the telephone
	 */
	public int getTelephone() {
		return telephone;
	}

	@Override
	public String getTitle() {
		return getName();
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param birth
	 *            the birth to set
	 */
	public void setBirth(final Date birth) {
		this.birth = birth;
	}

	/**
	 * @param dateModified
	 *            the dateModified to set
	 */
	@Override
	public void setDateModified(final Date dateModified) {
		this.dateModified = dateModified;
	}

	@Override
	public void setDescription(String description) {
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(final String email) {
		this.email = email;
	}

	/**
	 * @param enabled
	 *            the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param telephone
	 *            the telephone to set
	 */
	public void setTelephone(final int telephone) {
		this.telephone = telephone;
	}

	@Override
	public void setTitle(String title) {
		setName(title);
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
}
