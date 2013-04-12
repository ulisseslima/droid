package com.dvlcube.droid.bean;

import java.security.Principal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.dvlcube.droid.service.rr.NewUserRequest;
import com.dvlcube.reflection.FieldName;
import com.dvlcube.service.BasicInfo;
import com.dvlcube.util.StringUtils;

/**
 * @author wonka
 * @since 07/08/2012
 */
@Entity
public class User implements BasicInfo, Principal {
	public enum Field implements FieldName {
		birth, email, events, id, name, telephone
	}

	private static final long serialVersionUID = 3262522508885048363L;
	private Date birth; // datetime
	private Date dateModified; // timestamp
	private String email;
	private boolean enabled;
	@OneToMany
	private Set<Event> events;
	private String fullname;
	@Id
	@GeneratedValue
	private Long id; // id
	private String name;
	private String password;
	private Integer telephone;

	public User() {

	}

	/**
	 * @param userRequest
	 * @author wonka
	 * @since 24/03/2013
	 */
	public User(NewUserRequest userRequest) {
		name = userRequest.getUsername();
		password = userRequest.getPassword();
		telephone = 1234567890;
		enabled = true;
	}

	public User(final String name) {
		this.name = name;
		enabled = true;
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
		User other = (User) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
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
	public Set<Event> getEvents() {
		return events;
	}

	@Override
	public FieldName[] getFields() {
		return Field.values();
	}

	/**
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
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
	@Override
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
	public Integer getTelephone() {
		return telephone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (name == null ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean hasRequiredAttributes() {
		if (email == null) {
			return false;
		}
		if (name == null) {
			return false;
		}
		if (password == null) {
			return false;
		}
		return true;
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
	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	/**
	 * @param fullname
	 *            the fullname to set
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
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
	public void setTelephone(final Integer telephone) {
		this.telephone = telephone;
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
