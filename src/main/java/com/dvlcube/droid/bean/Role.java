package com.dvlcube.droid.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.dvlcube.reflection.FieldName;
import com.dvlcube.service.BasicInfo;
import com.dvlcube.util.StringUtils;

/**
 * 
 * @author wonka
 * @since 16/03/2013
 */
@Entity
public class Role implements BasicInfo {
	public enum Field implements FieldName {
		authority, id, user
	}

	private static final long serialVersionUID = 5642280917182146894L;
	private String authority;
	private Date dateModified;
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private User user;

	/**
	 * Creates a new Role for the given User.
	 * 
	 * @param user
	 *            User to add a new Role to.
	 * @author wonka
	 * @since 24/03/2013
	 */
	public Role(User user) {
		authority = "ROLE_USER";
		this.user = user;
		dateModified = new Date();
	}

	/**
	 * @return the authority
	 */
	public String getAuthority() {
		return authority;
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
		return authority;
	}

	@Override
	public String getName() {
		return authority;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	@Override
	public boolean hasRequiredAttributes() {
		if (user == null) {
			return false;
		}
		if (authority == null) {
			return false;
		}
		return true;
	}

	/**
	 * @param authority
	 *            the authority to set
	 */
	public void setAuthority(String authority) {
		this.authority = authority;
	}

	/**
	 * @param dateModified
	 *            the dateModified to set
	 */
	@Override
	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	@Override
	public void setDescription(String description) {
		throw new UnsupportedOperationException("can't set description on " + getClass());
	}

	/**
	 * @param id
	 *            the id to set
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public void setName(String name) {
		setAuthority(name);
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
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
